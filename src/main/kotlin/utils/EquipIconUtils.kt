package org.iris.wiki.utils

import kotlinx.coroutines.runBlocking
import org.iris.wiki.config.CommonConfig
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import javax.imageio.ImageIO

object EquipIconUtils {

    private const val EQUIP_ATLAS_URL = "https://wiki.biligame.com/blhx/%E8%A3%85%E5%A4%87%E5%9B%BE%E9%89%B4"
    private val remoteUrlCache = ConcurrentHashMap<String, String?>()

    fun resolveEquipIcon(name: String, tno: Int, fallbackPic: String = ""): String {
        val localFile = File("${CommonConfig.equip_path}/${sanitizeName(name)}.png")
        if (localFile.exists()) {
            return localFile.path
        }

        val fallbackUrl = normalizeWikiUrl(fallbackPic)
        if (fallbackUrl.isNotBlank() && cacheToLocal(localFile, fallbackUrl)) {
            return localFile.path
        }

        val remoteUrl = findEquipIconUrl(name, tno)
            ?: fallbackUrl.takeIf { it.isNotBlank() }
            ?: return fallbackPic

        return if (cacheToLocal(localFile, remoteUrl)) {
            localFile.path
        } else {
            remoteUrl
        }
    }

    private fun findEquipIconUrl(name: String, tno: Int): String? {
        val cacheKey = "$name#$tno"
        return remoteUrlCache.computeIfAbsent(cacheKey) {
            val doc = Jsoup.parse(runBlocking { HttpUtils.get(EQUIP_ATLAS_URL) }, EQUIP_ATLAS_URL)
            findIconUrlInAtlas(doc, name, tno) ?: findIconUrlInAtlas(doc, name, -1)
        }
    }

    private fun findIconUrlInAtlas(doc: Document, name: String, tno: Int): String? {
        val anchor = doc.select("a[title]").firstOrNull {
            val title = it.attr("title")
            title.substringBefore("#") == name && (tno < 0 || title.endsWith("#T$tno"))
        } ?: return null

        val image = anchor.selectFirst("img") ?: return null
        return bestImageUrl(image)
    }

    private fun bestImageUrl(image: Element): String? {
        val srcSet = image.attr("srcset")
        if (srcSet.isNotBlank()) {
            return srcSet.split(",")
                .map { it.trim().substringBeforeLast(" ") }
                .lastOrNull { it.isNotBlank() }
        }
        return image.absUrl("src").ifBlank { image.attr("src") }
    }

    private fun cacheToLocal(localFile: File, remoteUrl: String): Boolean {
        return runCatching {
            localFile.parentFile?.mkdirs()
            val image = ImageUtil.getImage(remoteUrl)
            ImageIO.write(image, "png", localFile)
        }.isSuccess
    }

    private fun normalizeWikiUrl(url: String): String {
        if (url.isBlank()) {
            return ""
        }
        return when {
            url.startsWith("http://") || url.startsWith("https://") -> url
            url.startsWith("//") -> "https:$url"
            url.startsWith("/") -> "https://wiki.biligame.com$url"
            else -> url
        }
    }

    private fun sanitizeName(name: String): String {
        return name.replace("\\", "_").replace("/", "_")
    }
}

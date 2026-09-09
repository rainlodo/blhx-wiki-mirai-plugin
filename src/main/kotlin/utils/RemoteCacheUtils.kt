package org.iris.wiki.utils

import org.iris.wiki.config.CommonConfig
import java.io.File
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.concurrent.ConcurrentHashMap

object RemoteCacheUtils {

    private const val CACHE_TTL_MILLIS = 30 * 60 * 1000L

    private val textCacheDir = File(CommonConfig.network_text_cache_path)
    private val binaryCacheDir = File(CommonConfig.network_binary_cache_path)
    private val urlLocks = ConcurrentHashMap<String, Any>()

    fun getText(url: String, loader: () -> String): String {
        val cacheFile = getCacheFile(textCacheDir, url)
        val staleValue = cacheFile.takeIf { it.exists() }?.readText(StandardCharsets.UTF_8)
        if (isCacheValid(cacheFile)) {
            return staleValue ?: ""
        }
        synchronized(urlLocks.computeIfAbsent(url) { Any() }) {
            val lockedStaleValue = cacheFile.takeIf { it.exists() }?.readText(StandardCharsets.UTF_8)
            if (isCacheValid(cacheFile)) {
                return lockedStaleValue ?: ""
            }
            val latestValue = loader()
            if (latestValue.isNotEmpty()) {
                cacheFile.parentFile?.mkdirs()
                cacheFile.writeText(latestValue, StandardCharsets.UTF_8)
                return latestValue
            }
            return lockedStaleValue ?: ""
        }
    }

    fun getBinary(url: String, loader: () -> ByteArray?): ByteArray? {
        val cacheFile = getCacheFile(binaryCacheDir, url)
        val staleValue = cacheFile.takeIf { it.exists() }?.readBytes()
        if (isCacheValid(cacheFile)) {
            return staleValue
        }
        synchronized(urlLocks.computeIfAbsent(url) { Any() }) {
            val lockedStaleValue = cacheFile.takeIf { it.exists() }?.readBytes()
            if (isCacheValid(cacheFile)) {
                return lockedStaleValue
            }
            val latestValue = loader()
            if (latestValue != null && latestValue.isNotEmpty()) {
                cacheFile.parentFile?.mkdirs()
                cacheFile.writeBytes(latestValue)
                return latestValue
            }
            return lockedStaleValue
        }
    }

    fun clearAll() {
        deleteDirectoryFiles(textCacheDir)
        deleteDirectoryFiles(binaryCacheDir)
    }

    private fun isCacheValid(file: File): Boolean {
        if (!file.exists()) {
            return false
        }
        return System.currentTimeMillis() - file.lastModified() <= CACHE_TTL_MILLIS
    }

    private fun getCacheFile(dir: File, url: String): File {
        return File(dir, "${hashUrl(url)}.cache")
    }

    private fun hashUrl(url: String): String {
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest(url.toByteArray(StandardCharsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun deleteDirectoryFiles(directory: File?) {
        if (directory == null || !directory.exists() || !directory.isDirectory) {
            return
        }
        directory.listFiles()?.forEach {
            if (it.isDirectory) {
                deleteDirectoryFiles(it)
            }
            it.delete()
        }
    }
}

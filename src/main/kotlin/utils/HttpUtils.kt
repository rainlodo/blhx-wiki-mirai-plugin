package org.iris.wiki.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.Duration

object HttpUtils {

    private val cookie: String = ""

    private var client: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(Duration.ofMillis(20000))
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", ua.random())
                .header("Referer", "https://wiki.biligame.com/")
                .build()
            chain.proceed(request)
        }
        .build()

    private val ua = listOf(
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:133.0) Gecko/20100101 Firefox/133.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/18.2 Safari/605.1.15",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:133.0) Gecko/20100101 Firefox/133.0"
    )

    private fun sendRequest(request: Request): String {
        return try {
            client.newCall(request).execute().use { response ->
                response.body?.string() ?: ""
            }
        } catch (_: Exception) {
            ""
        }
//        return json.parseToJsonElement(body)
    }

    private fun sendByteRequest(request: Request): ByteArray? {
        return try {
            client.newCall(request).execute().use { response ->
                response.body?.bytes()
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun buildGetRequest(url: String): Request {
        return Request.Builder()
            .url(url)
            .header("cookie", cookie)
            .header("Content-Type", "application/json; charset=utf-8")
            .header("user-agent", ua.random())
            // 修改 get/post 方法中的 header 部分
            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
            .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
            .header("Cache-Control", "max-age=0")
            .header("Upgrade-Insecure-Requests", "1")
            .get()
            .build()
    }

    suspend fun get(url: String, useCache: Boolean = true): String = withContext(Dispatchers.IO) {
        val request = buildGetRequest(url)
        if (useCache) {
            RemoteCacheUtils.getText(url) { sendRequest(request) }
        } else {
            sendRequest(request)
        }
    }

    fun post(url: String, postBody: String): String {
        val media = "application/x-www-form-urlencoded; charset=utf-8"
        val request = Request.Builder().url(url)
            .header("Content-Type", media)
            .header("user-agent", ua.random())
            .post(postBody.toRequestBody(media.toMediaTypeOrNull())).build()
        return sendRequest(request)
    }


    fun getBytes(url: String, useCache: Boolean = true): ByteArray? {
        val request = buildGetRequest(url)
        return if (useCache) {
            RemoteCacheUtils.getBinary(url) { sendByteRequest(request) }
        } else {
            sendByteRequest(request)
        }
    }

    fun getByteArray(url: String, useCache: Boolean = true): ByteArrayOutputStream? {
        val bytes = getBytes(url, useCache) ?: return null
        return ByteArrayOutputStream(bytes.size).apply {
            ByteArrayInputStream(bytes).copyTo(this)
        }
    }

    fun clearRemoteCache() {
        RemoteCacheUtils.clearAll()
    }
}

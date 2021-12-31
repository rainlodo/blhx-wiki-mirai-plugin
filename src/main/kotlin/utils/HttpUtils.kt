package org.iris.wiki.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.time.Duration

object HttpUtils {

    private val cookie: String = ""

    private var client: OkHttpClient = OkHttpClient().newBuilder().connectTimeout(Duration.ofMillis(20000)).build()

    private val ua = listOf(
        "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US);",
        "Mozilla/5.0 (compatible; MSIE 10.0; Macintosh; Intel Mac OS X 10_7_3; Trident/6.0)'",
        "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.2; SV1; .NET CLR 3.3.69573; WOW64; en-US)",
        "Opera/9.80 (X11; Linux i686; U; ru) Presto/2.8.131 Version/11.11",
        "Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1",
        "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:15.0) Gecko/20100101 Firefox/15.0.1",
        "Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25"
    )

    private fun sendRequest(request: Request): String {
        val response = client.newCall(request).execute()
        val body: String = response.body!!.string()
//        return json.parseToJsonElement(body)
        return body
    }


    fun get(url: String): String {
        val request = Request.Builder().url(url)
            .header("cookie", cookie)
            .header("Content-Type", "application/json; charset=utf-8")
            .header("user-agent", ua.random())
            .get().build()
        return sendRequest(request)
    }

    fun post(url: String, postBody: String): String {
        val media = "application/x-www-form-urlencoded; charset=utf-8"
        val request = Request.Builder().url(url)
            .header("Content-Type", media)
            .header("user-agent", ua.random())
            .post(postBody.toRequestBody(media.toMediaTypeOrNull())).build()
        return sendRequest(request)
    }


    fun getByteArray(url: String) : ByteArrayOutputStream? {
        try{

            val request = Request.Builder().url(url)
                .header("cookie", cookie)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("user-agent", ua.random())
                .get().build()
            val infoStream = ByteArrayOutputStream()
            val response = client.newCall(request).execute();

            val `in` = response.body?.byteStream()
            val buffer = ByteArray(2048)
            var len = 0
            val data = ""
            if (`in` != null) {
                while (`in`.read(buffer).also { len = it } > 0) {
                    infoStream.write(buffer, 0, len)
                }
            }
            infoStream.write((Math.random() * 100).toInt() + 1)
            infoStream.close()
            return infoStream
        }
        catch (e: Exception) {
            return null
        }
    }

}
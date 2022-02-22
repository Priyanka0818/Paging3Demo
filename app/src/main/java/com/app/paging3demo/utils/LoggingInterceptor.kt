package com.app.paging3demo.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        var requestLog = String.format(
            "Sending request %s on %s%n%s",
            request.url, chain.connection(), request.headers
        )
        if (request.method.compareTo("post", ignoreCase = true) == 0) {
            requestLog = """
                
                $requestLog
                ${bodyToString(request)}
                """.trimIndent()
        }
        Log.e("LoggingInterceptor", "request\n$requestLog")
        val response: Response = chain.proceed(request)
        val bodyString = response.body!!.string()
        Log.e("LoggingInterceptor", "response\n$bodyString")
        return response.newBuilder()
            .body(bodyString.toResponseBody(response.body!!.contentType()))
            .build()
    }

    companion object {
        fun bodyToString(request: Request): String {
            return try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body!!.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}
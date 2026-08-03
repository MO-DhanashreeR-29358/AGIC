package com.mosl.mobile.coredata.util.interceptors

import android.content.Context
import android.os.Environment
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ApiLoggerInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestTime = System.currentTimeMillis()

        // Capture request body
        val requestBodyStr = try {
            val buffer = Buffer()
            request.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            "(unable to read request body)"
        }

        val response = chain.proceed(request)
        val durationMs = System.currentTimeMillis() - requestTime

        // Capture response body (peek so it can still be consumed by Retrofit)
        val responseBodyStr = try {
            val source = response.peekBody(Long.MAX_VALUE)
            source.string()
        } catch (e: Exception) {
            "(unable to read response body)"
        }

        writeLog(
            url = request.url.toString(),
            method = request.method,
            requestHeaders = request.headers.toString(),
            requestBody = requestBodyStr,
            responseCode = response.code,
            responseHeaders = response.headers.toString(),
            responseBody = responseBodyStr,
            durationMs = durationMs
        )

        return response
    }

    private fun writeLog(
        url: String,
        method: String,
        requestHeaders: String,
        requestBody: String,
        responseCode: Int,
        responseHeaders: String,
        responseBody: String,
        durationMs: Long
    ) {
        try {
            val now = Date()
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now)
           // val timeStr = SimpleDateFormat("HH-mm-ss", Locale.getDefault()).format(now)
            val logTimeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(now)

         /*   val fileName = "client_${dateStr}.txt"
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val logFile = File(downloadsDir, fileName)*/

            val fileName = "client_${dateStr}.txt"
            val downloadsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                ?: context.filesDir            // fallback to internal storage
            if (!downloadsDir.exists()) downloadsDir.mkdirs()
            val logFile = File(downloadsDir, fileName)

            val separator = "=".repeat(60)
            val entry = buildString {
                appendLine(separator)
                appendLine("TIMESTAMP : $logTimeStamp")
                appendLine("URL       : $url")
                appendLine("METHOD    : $method")
                appendLine("--- REQUEST HEADERS ---")
                appendLine(requestHeaders.trim())
                appendLine("--- REQUEST BODY ---")
                appendLine(requestBody.ifBlank { "(empty)" })
                appendLine("--- RESPONSE ($responseCode) [${durationMs}ms] ---")
                appendLine("--- RESPONSE HEADERS ---")
                appendLine(responseHeaders.trim())
                appendLine("--- RESPONSE BODY ---")
                appendLine(responseBody.ifBlank { "(empty)" })
                appendLine(separator)
                appendLine()
            }

           // FileWriter(logFile, true).use { it.write(entry) }
        } catch (e: Exception) {
            // Silently ignore file write failures — never crash the app for logging
        }
    }
}

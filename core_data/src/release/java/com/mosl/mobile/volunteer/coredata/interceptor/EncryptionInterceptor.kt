package com.mosl.mobile.volunteer.coredata.interceptor

import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.volunteer.annotations.*
import com.mosl.shared.AppDateFormats
import com.mosl.shared.Encryptor.AESEncryptor
import com.mosl.shared.util.getFormatedDate
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException
import javax.inject.Inject

class EncryptionInterceptor @Inject constructor(
    val encryptor: AESEncryptor,
    @LastScheduleDate
    val scheduleDate: StringPreference,
    @LastCorporateScheduleDate
    val corporateScheduleDate: StringPreference,
    @LastNotificationDate
    val notificationDate: StringPreference,
    @LastBytesDate
    val bytesDate: StringPreference,
    @ScheduleData
    val scheduleData: StringPreference,
    @CorporateScheduleData
    val corporateScheduleData: StringPreference
) : Interceptor {

    /*@Throws(IOException::class)
     override fun intercept(chain: Interceptor.Chain): Response {
         var request = chain.request()
         val oldBody = request.body
         var isEncrypted = false
         val last = request.url
             .pathSegments
             .last()
         if (oldBody != null) {
             val buffer = Buffer()
             oldBody.writeTo(buffer)
             val strOldBody = buffer.readUtf8()


             val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
             val strNewBody = encryptor.encrypt(strOldBody)
             val body = strNewBody.toRequestBody(mediaType)
             request = request.newBuilder()
                 .method(request.method, body)
                 .build()
             isEncrypted = true
         }

         val response = chain.proceed(request)
         if (isEncrypted && response.body != null) {
             try {
                 val strResponse = response.body
                 val headerDate = response.header("Date") ?: ""
                 if (scheduleDate.get().isNotEmpty()) {

                     val formatedScheduleDate = getFormatedDate(
                         scheduleDate.get(),
                         AppDateFormats.DF_JSON_DATE_12,
                         AppDateFormats.DF_JSON_DATE_13
                     )
                     if (headerDate.contains(formatedScheduleDate) && scheduleData.isSet) {
                         scheduleData.delete()
                     }

                 }

                 if (corporateScheduleDate.get().isNotEmpty())
                 {
                     val formatedCorpScheduleDate = getFormatedDate(
                         corporateScheduleDate.get(),
                         AppDateFormats.DF_JSON_DATE_12,
                         AppDateFormats.DF_JSON_DATE_13
                     )
                     if (headerDate.contains(formatedCorpScheduleDate) && corporateScheduleData.isSet) {
                         corporateScheduleData.delete()
                     }
                 }

                 if (response.code == 200 && last.contains("GetScheduleAPI")) {
                     scheduleDate.set(headerDate)
                 }
                 if (response.code == 200 && last.contains("GetbyteDetails")) {
                     bytesDate.set(headerDate)
                 }
                 if (response.code == 200 && last.contains("GetCorporateSchedule")) {
                     corporateScheduleDate.set(headerDate)
                 }

                 if (response.code == 200 && last.contains("NotificationHistory")) {
                     notificationDate.set(headerDate)
                 }

                 val decrypted = encryptor.decrypt(strResponse!!.string())
                 if (decrypted != null) {
                     val decryptedBody =
                         decrypted.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
                     return response.newBuilder()
                         .body(decryptedBody)
                         .build()
                 } else {
                     return response;
                 }
             } catch (e: Exception) {
                 return response
             }
         } else {
             return response
         }
     }*/

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val last = request.url.pathSegments.last()

        // log request
        val requestBody = request.body
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            Log.i("API_REQUEST", "URL: ${request.url}")
            Log.i("API_REQUEST", "Method: ${request.method}")
            Log.i("API_REQUEST", "Headers: ${request.headers}")
            Log.i("API_REQUEST", "Body: ${buffer.readUtf8()}")
        } else {
            Log.i("API_REQUEST", "URL: ${request.url} | Method: ${request.method} | No Body")
        }

        val response = chain.proceed(request)

        // log response
        val responseBody = response.body
        if (responseBody != null) {
            val responseBodyString = responseBody.string()
            Log.i("API_RESPONSE", "URL: ${request.url}")
            Log.i("API_RESPONSE", "Code: ${response.code}")
            Log.i("API_RESPONSE", "Body: $responseBodyString")

            val rebuiltBody = responseBodyString.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val rebuiltResponse = response.newBuilder().body(rebuiltBody).build()

            val headerDate = rebuiltResponse.header("Date") ?: ""
            if (scheduleDate.get().isNotEmpty()) {
                val formatedScheduleDate = getFormatedDate(scheduleDate.get(), AppDateFormats.DF_JSON_DATE_12, AppDateFormats.DF_JSON_DATE_13)
                if (headerDate.contains(formatedScheduleDate) && scheduleData.isSet) scheduleData.delete()
            }
            if (corporateScheduleDate.get().isNotEmpty()) {
                val formatedCorpScheduleDate = getFormatedDate(corporateScheduleDate.get(), AppDateFormats.DF_JSON_DATE_12, AppDateFormats.DF_JSON_DATE_13)
                if (headerDate.contains(formatedCorpScheduleDate) && corporateScheduleData.isSet) corporateScheduleData.delete()
            }
            if (response.code == 200 && last.contains("GetScheduleAPI")) scheduleDate.set(headerDate)
            if (response.code == 200 && last.contains("GetbyteDetails")) bytesDate.set(headerDate)
            if (response.code == 200 && last.contains("GetCorporateSchedule")) corporateScheduleDate.set(headerDate)
            if (response.code == 200 && last.contains("NotificationHistory")) notificationDate.set(headerDate)

            return rebuiltResponse
        }

        return response
    }



}
package com.mosl.mobile.coredata.interceptor

import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.*
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

    @Throws(IOException::class)
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
                if (scheduleDate.isSet) {

                    val formatedScheduleDate = getFormatedDate(
                        scheduleDate.get(),
                        AppDateFormats.DF_JSON_DATE_12,
                        AppDateFormats.DF_JSON_DATE_13
                    )

                    if (headerDate.contains(formatedScheduleDate) && scheduleData.isSet) {
                        scheduleData.delete()
                    }
                }

                if (corporateScheduleDate.isSet)
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

                if (response.code == 200 && last.contains("GetCorporateSchedule")) {
                    corporateScheduleDate.set(headerDate)
                }

                if (response.code == 200 && last.contains("NotificationHistory")) {
                    notificationDate.set(headerDate)
                }

                if (response.code == 200 && last.contains("GetbyteDetails")) {
                    bytesDate.set(headerDate)
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
    }
}
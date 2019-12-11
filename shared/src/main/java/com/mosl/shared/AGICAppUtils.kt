package com.mosl.shared.util

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.provider.Settings
import com.mosl.shared.AppDateFormats.*
import java.net.Inet4Address
import java.net.NetworkInterface
import java.text.DateFormat
import java.text.ParseException
import java.util.*


fun getLocalIpAddress(): String {
    try {
        val en = NetworkInterface.getNetworkInterfaces()
        while (en.hasMoreElements()) {
            val intf = en.nextElement()
            val enumIpAddr = intf.inetAddresses
            while (enumIpAddr.hasMoreElements()) {
                val inetAddress = enumIpAddr.nextElement()
                if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                    return inetAddress.getHostAddress()
                }
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return "127.0.0.1"
}

fun getDeviceID(contentResolver: ContentResolver): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

fun hasGPSDevice(context: Context): Boolean {
    val mgr = context
        .getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers = mgr.allProviders ?: return false
    return providers.contains(LocationManager.GPS_PROVIDER)
}

fun isLocationFromMockProvider(
    context: Context,
    location: Location
): Boolean {
    val isMock: Boolean
    if (Build.VERSION.SDK_INT >= 18) {
        isMock = location.isFromMockProvider
    } else {
        isMock = isMockSettingsON(context)
    }
    return isMock
}

@Suppress("DEPRECATION")
fun isMockSettingsON(context: Context): Boolean {
    return Settings.Secure.getString(
        context.contentResolver, Settings.Secure.ALLOW_MOCK_LOCATION
    ) != "0"
}


fun getTime(startDateTime: String, endDateTime: String): String {
    val startTimeFormat = DF_TIME_HH_ss.parse(startDateTime.split()) ?: "00:00:00"
    val endTimeFormat = DF_TIME_HH_ss.parse(endDateTime.split()) ?: "00:00:00"
    val startTime = DF_TIME_hh.format(startTimeFormat)
    val endTime = DF_TIME_hh.format(endTimeFormat)
    return startTime + '-' + endTime
}

fun String.split(): String {
    return split("T").toTypedArray()[1]
}


fun getTimeAMPM(dateTime: String): String {

    val timeFormat = DF_TIME_HH_ss.parse(dateTime.split()) ?: "00:00:00"
    val time = DF_TIME_hh.format(timeFormat)

    return time
}

fun checkTimeIsBetween(starTime: String, endTime: String): Boolean {
    try {

        val startTimeDate = DF_TIME_hh.parse(starTime)
        val startTimeCalendar = Calendar.getInstance()
        startTimeCalendar.time = startTimeDate!!
        startTimeCalendar.add(Calendar.DATE, 1)


        val endTimeDate = DF_TIME_hh.parse(endTime)
        val endTimeCalendar = Calendar.getInstance()
        endTimeCalendar.time = endTimeDate!!
        endTimeCalendar.add(Calendar.DATE, 1)

        val currentTimeCalendar = Calendar.getInstance()
        val currentTime = DF_TIME_hh.format(currentTimeCalendar.time)

        val currentTimeDate = DF_TIME_hh.parse(currentTime)
        currentTimeCalendar.time = currentTimeDate!!
        currentTimeCalendar.add(Calendar.DATE, 1)

        currentTimeCalendar.time.apply {
            if (after(startTimeCalendar.time) && before(endTimeCalendar.time)) {
                return true
            } else if (after(endTimeCalendar.time)) {
                return false
            } else if (before(endTimeCalendar.time)) {
                return true
            }
        }

    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return false
}

fun getFormatedDate(
    date: String,
    inputFormatedDate: DateFormat,
    outputFormatedDate: DateFormat
): String {
    val time = inputFormatedDate.parse(date) ?: "0000-00-00'T'00:00:00"
    return outputFormatedDate.format(time)
}

fun getTodaysDate(): String {

    val calendar3 = Calendar.getInstance()
    val currentDate = df_Date6.format(calendar3.time)


    return currentDate
}

fun addToCalendart(
    context: Context,
    title: String,
    description: String,
    location: String,
    startTime: String,
    endTime: String
) {

    val intent = Intent(Intent.ACTION_INSERT)
        .setData(Events.CONTENT_URI)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
        .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
        .putExtra(Events.TITLE, title)
        .putExtra(Events.DESCRIPTION, description)
        .putExtra(Events.EVENT_LOCATION, location)
        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)

    context.startActivity(intent)


}

fun getMilliFromDate(dateFormat: String, formaterDate: DateFormat): String {
    val date = formaterDate.parse(dateFormat)

    return date?.time.toString()
}
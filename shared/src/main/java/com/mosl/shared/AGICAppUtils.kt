package com.mosl.shared.util

import android.content.ContentResolver
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import com.mosl.shared.AppDateFormats.*
import java.net.Inet4Address
import java.net.NetworkInterface
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.provider.CalendarContract.Events
import android.provider.CalendarContract


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
    val startTimeFormat = DF_TIME_HH_ss.parse(startDateTime.split())
    val endTimeFormat = DF_TIME_HH_ss.parse(endDateTime.split())
    val startTime = DF_TIME_hh.format(startTimeFormat)
    val endTime = DF_TIME_hh.format(endTimeFormat)
    return startTime + '-' + endTime
}

fun String.split(): String {
    return split("T").toTypedArray()[1]
}


fun getTimeAMPM(dateTime: String): String {

    val timeFormat = DF_TIME_HH_ss.parse(dateTime.split())
    val time = DF_TIME_hh.format(timeFormat)

    return time
}

fun checkTimeIsBetween(string1: String, string2: String): Boolean {
    try {

        val time1 = SimpleDateFormat("hh:mm a").parse(string1)
        val calendar1 = Calendar.getInstance()
        calendar1.time = time1
        calendar1.add(Calendar.DATE, 1)


        val time2 = SimpleDateFormat("hh:mm a").parse(string2)
        val calendar2 = Calendar.getInstance()
        calendar2.time = time2
        calendar2.add(Calendar.DATE, 1)

        val calendar3 = Calendar.getInstance()
        val sdf = SimpleDateFormat("hh:mm a")
        val currentTime = sdf.format(calendar3.time)

        val d = sdf.parse(currentTime)
        calendar3.time = d
        calendar3.add(Calendar.DATE, 1)

        val x = calendar3.time
        if (x.after(calendar1.time) && x.before(calendar2.time)) {
            return true
        } else if (x.after(calendar2.time)) {
            return false
        } else if (x.before(calendar2.time)) {
            return true
        }

    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return false
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
    if (Build.VERSION.SDK_INT >= 14) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
            .putExtra(Events.TITLE, title)
            .putExtra(Events.DESCRIPTION, description)
            .putExtra(Events.EVENT_LOCATION, location)
            .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
//            .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")

        context.startActivity(intent)

    } else {
        val cal = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra("beginTime", startTime)
        intent.putExtra("allDay", true)
        intent.putExtra("rrule", "FREQ=YEARLY")
        intent.putExtra("endTime", endTime)
        intent.putExtra("title", title)
        context.startActivity(intent)
    }
}
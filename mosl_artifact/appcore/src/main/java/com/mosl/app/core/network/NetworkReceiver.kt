package com.mosl.app.core.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.Locale

class NetworkReceiver(
    private var currentState: NetworkChangeEvent
) : BroadcastReceiver() {
  private val networkStateSubject: BehaviorSubject<NetworkChangeEvent> = BehaviorSubject.createDefault(currentState)

  override fun onReceive(
      context: Context,
      intent: Intent?
  ) {
    val event = getCurrentState(context)
    currentState = event
    networkStateSubject.onNext(event)
  }

  fun onNetworkChanged(): Observable<NetworkChangeEvent> {
    return networkStateSubject
  }

  companion object {
    const val WIFI = "wifi"
    const val WIMAX = "wimax"
    // mobile
    const val MOBILE = "mobile"
    // Android L calls this Cellular, because I have no idea!
    const val CELLULAR = "cellular"
    // 2G network types
    const val GSM = "gsm"
    const val GPRS = "gprs"
    const val EDGE = "edge"
    // 3G network types
    const val CDMA = "cdma"
    const val UMTS = "umts"
    const val HSPA = "hspa"
    const val HSUPA = "hsupa"
    const val HSDPA = "hsdpa"
    const val ONEXRTT = "1xrtt"
    const val EHRPD = "ehrpd"
    // 4G network types
    const val LTE = "lte"
    const val UMB = "umb"
    const val HSPA_PLUS = "hspa+"
    // return type
    const val TYPE_UNKNOWN = "unknown"
    const val TYPE_ETHERNET = "ethernet"
    const val TYPE_WIFI = "wifi"
    const val TYPE_2G = "2g"
    const val TYPE_3G = "3g"
    const val TYPE_4G = "4g"
    const val TYPE_NONE = "none"

    fun getCurrentState(context: Context): NetworkChangeEvent {
      val networkInfo = context.getNetworkInfo()
      return if (networkInfo == null) {
        NetworkChangeEvent(false, TYPE_NONE)
      } else {
        NetworkChangeEvent(networkInfo.isConnected, getType(networkInfo))
      }
    }

    /**
     * Determine the type of connection
     *
     * @param info the network info so we can determine connection type.
     * @return the type of mobile network we are on
     */
    private fun getType(info: NetworkInfo): String {
      val type = info.typeName.toLowerCase(Locale.US)
      return when {
        type == WIFI -> TYPE_WIFI
        type.toLowerCase() == TYPE_ETHERNET -> TYPE_ETHERNET
        type == MOBILE || type == CELLULAR -> {
          val subType = info.subtypeName.toLowerCase(Locale.US)
          when {
            subType == GSM
                || subType == GPRS
                || subType == EDGE
            -> TYPE_2G
            subType.startsWith(CDMA)
                || subType == UMTS
                || subType == ONEXRTT
                || subType == EHRPD
                || subType == HSUPA
                || subType == HSDPA
                || subType == HSPA
            -> TYPE_3G
            subType == LTE
                || subType == UMB
                || subType == HSPA_PLUS
            -> TYPE_4G
            else -> TYPE_UNKNOWN
          }
        }
        else -> TYPE_UNKNOWN
      }
    }
  }
}
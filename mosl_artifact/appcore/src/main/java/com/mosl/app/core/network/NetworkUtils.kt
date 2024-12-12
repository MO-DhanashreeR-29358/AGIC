@file:JvmName("NetworkUtils")

package com.mosl.app.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.getNetworkInfo(): NetworkInfo? {
  val cm =
      applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  return cm.activeNetworkInfo
}

fun Context.isNetworkConnected(): Boolean {
  val activeNetwork = this.getNetworkInfo()
  return activeNetwork != null && activeNetwork.isConnected
}
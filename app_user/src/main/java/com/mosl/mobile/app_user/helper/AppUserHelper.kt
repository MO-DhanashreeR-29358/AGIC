package com.mosl.mobile.app_user.helper

import android.content.Context
import android.content.SharedPreferences

fun Context.getPref(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}


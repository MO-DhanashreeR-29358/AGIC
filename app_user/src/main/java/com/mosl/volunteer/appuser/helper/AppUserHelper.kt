package com.mosl.volunteer.appuser.helper

import android.content.Context
import android.content.SharedPreferences

fun Context.getPref(name: String): SharedPreferences {
    return getSharedPreferences(name, Context.MODE_PRIVATE)
}


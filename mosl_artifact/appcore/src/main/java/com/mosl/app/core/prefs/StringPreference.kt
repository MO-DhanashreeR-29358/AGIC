package com.mosl.app.core.prefs

import android.content.SharedPreferences

/**
 * Created by lalkishore on 6/9/2016.
 */
class StringPreference @JvmOverloads constructor(
    preferences: SharedPreferences,
    key: String,
    private val defaultValue: String? = null
) : Preference<Boolean>(preferences, key) {

  fun get(): String {
    return preferences.getString(key, defaultValue) ?: ""
  }

  fun set(value: String) {
    preferences.edit().putString(key, value).apply()
  }
}
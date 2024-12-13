package com.mosl.app.core.prefs

import android.content.SharedPreferences

/**
 * Created by lalkishore on 6/9/2016.
 */
class BooleanPreference @JvmOverloads constructor(
    preferences: SharedPreferences,
    key: String,
    private val defaultValue: Boolean = false
) : Preference<Boolean>(preferences, key) {

  fun get(): Boolean {
    return preferences.getBoolean(key, defaultValue)
  }

  fun set(value: Boolean) {
    preferences.edit().putBoolean(key, value).apply()
  }
}

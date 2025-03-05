package com.mosl.token.manager.prefs

import com.mosl.app.core.prefs.StringPreference

fun StringPreference.getOrDefault(default: String): String {
  return get() ?: default
}

val StringPreference.isValid: Boolean
  get() {
    return isSet && get().isNotEmpty()
  }
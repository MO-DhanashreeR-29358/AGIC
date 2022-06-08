@file:JvmName("CrashlyticsHelper")

package com.mosl.thunder

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent

fun logCustomEvent(customEvent: CustomEvent?) {
  try {
    if (customEvent != null) {
      //TODO Add Connection Type.
      Answers.getInstance()
          .logCustom(customEvent)
    }
  } catch (ignored: Exception) {
  }
}
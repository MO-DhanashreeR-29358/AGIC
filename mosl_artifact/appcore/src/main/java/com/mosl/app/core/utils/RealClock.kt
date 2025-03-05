package com.mosl.app.core.utils

import android.os.SystemClock
import com.mosl.commons.utils.Clock

object RealClock : Clock {
  override fun getTimestamp(): Long {
    return SystemClock.elapsedRealtime()
  }
}
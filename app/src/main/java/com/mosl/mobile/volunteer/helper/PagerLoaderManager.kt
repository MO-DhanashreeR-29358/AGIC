package com.mosl.mobile.volunteer.helper

import com.mosl.mobile.volunteer.core.MOSLCommonFragment

interface PagerLoaderManager {
  fun onShowProgress(currFragment: MOSLCommonFragment)

  fun onHideProgress(currFragment: MOSLCommonFragment)
}
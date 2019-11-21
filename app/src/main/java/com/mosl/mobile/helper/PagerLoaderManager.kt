package com.mosl.mobile.helper

import com.mosl.mobile.core.MOSLCommonFragment

interface PagerLoaderManager {
  fun onShowProgress(currFragment: MOSLCommonFragment)

  fun onHideProgress(currFragment: MOSLCommonFragment)
}
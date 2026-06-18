package com.mosl.mobile.rm.helper

import com.mosl.mobile.rm.core.MOSLCommonFragment

interface PagerLoaderManager {
  fun onShowProgress(currFragment: MOSLCommonFragment)

  fun onHideProgress(currFragment: MOSLCommonFragment)
}
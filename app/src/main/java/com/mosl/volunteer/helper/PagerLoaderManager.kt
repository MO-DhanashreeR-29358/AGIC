package com.mosl.volunteer.helper

import com.mosl.volunteer.core.MOSLCommonFragment

interface PagerLoaderManager {
  fun onShowProgress(currFragment: MOSLCommonFragment)

  fun onHideProgress(currFragment: MOSLCommonFragment)
}
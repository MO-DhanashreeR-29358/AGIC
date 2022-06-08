package com.mosl.mobile.coreui.helper

import com.mosl.mobile.coreui.core.MOSLCommonFragment

interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
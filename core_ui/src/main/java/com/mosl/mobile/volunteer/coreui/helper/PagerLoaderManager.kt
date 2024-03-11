package com.mosl.mobile.volunteer.coreui.helper

import com.mosl.mobile.volunteer.coreui.core.MOSLCommonFragment

interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
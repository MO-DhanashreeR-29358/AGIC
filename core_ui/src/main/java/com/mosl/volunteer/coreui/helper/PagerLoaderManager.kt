package com.mosl.volunteer.coreui.helper

import com.mosl.volunteer.coreui.core.MOSLCommonFragment

interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
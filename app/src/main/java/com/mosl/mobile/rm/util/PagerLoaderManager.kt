package com.mosl.mobile.rm.util

import com.mosl.mobile.rm.core.MOSLCommonFragment


/**
 * Created by snehaganapuram on 2/5/2016.
 */
interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
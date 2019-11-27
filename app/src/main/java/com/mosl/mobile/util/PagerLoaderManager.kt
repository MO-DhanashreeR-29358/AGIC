package com.mosl.mobile.util

import com.mosl.mobile.core.MOSLCommonFragment


/**
 * Created by snehaganapuram on 2/5/2016.
 */
interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
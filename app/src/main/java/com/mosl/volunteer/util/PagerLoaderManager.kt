package com.mosl.volunteer.util

import com.mosl.volunteer.core.MOSLCommonFragment


/**
 * Created by snehaganapuram on 2/5/2016.
 */
interface PagerLoaderManager {
    fun onShowProgress(currFragment: MOSLCommonFragment)

    fun onHideProgress(currFragment: MOSLCommonFragment)
}
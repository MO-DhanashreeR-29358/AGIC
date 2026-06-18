package com.mosl.mobile.rm.ui

import android.content.Context
import com.mosl.debugview.model.DebugAppInfo
import com.mosl.mobile.rm.BuildConfig
import com.mosl.mobile.rm.R.string
import com.mosl.mobile.rm.core.MOSLCommonActivity
import com.mosl.mobile.rm.nav.DebugConstants
import com.mosl.mobile.rm.splash.SplashActivity
import dagger.Module
import dagger.Provides

@Module
class DebugUiModule {
    @Provides
    internal fun provideDebugAppInfo(context: Context): DebugAppInfo {
        return DebugAppInfo(
            context.getString(string.main_app_name),
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE,
            "",
            0,
            SplashActivity::class.java,
            MOSLCommonActivity::class.java,
            DebugConstants.NAVIGATE_TO_DASHBOARD
        )
    }
}
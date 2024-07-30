package com.mosl.mobile.ui

import android.content.Context
import com.mosl.debugview.model.DebugAppInfo
import com.mosl.mobile.BuildConfig
import com.mosl.mobile.R.string
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.nav.DebugConstants
import com.mosl.mobile.splash.SplashActivity
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
            123,
            SplashActivity::class.java,
            MOSLCommonActivity::class.java,
            DebugConstants.NAVIGATE_TO_DASHBOARD
        )
    }
}
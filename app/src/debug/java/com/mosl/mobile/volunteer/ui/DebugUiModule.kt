package com.mosl.mobile.volunteer.ui

import android.content.Context
import com.mosl.debugview.model.DebugAppInfo
import com.mosl.mobile.volunteer.BuildConfig
import com.mosl.mobile.volunteer.R.string
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.nav.DebugConstants
import com.mosl.mobile.volunteer.splash.SplashActivity
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
            BuildConfig.GIT_SHA,
            BuildConfig.GIT_TIMESTAMP,
            SplashActivity::class.java,
            MOSLCommonActivity::class.java,
            DebugConstants.NAVIGATE_TO_DASHBOARD
        )
    }
}
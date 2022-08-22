package com.mosl.volunteer.ui

import android.content.Context
import com.mosl.debugview.model.DebugAppInfo
import com.mosl.volunteer.BuildConfig
import com.mosl.volunteer.R.string
import com.mosl.volunteer.core.MOSLCommonActivity
import com.mosl.volunteer.nav.DebugConstants
import com.mosl.volunteer.splash.SplashActivity
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
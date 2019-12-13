package com.mosl.mobile.coredata

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.*
import com.mosl.mobile.app_user.BYTES_DATE_KEY
import com.mosl.mobile.app_user.IMAGE_SPLASH_URL_KEY
import com.mosl.mobile.app_user.REMOTE_CONFIG_DATA_KEY
import com.mosl.mobile.app_user.SCHEDULE_DATE_KEY
import com.mosl.mobile.app_user.helper.PrefStoreProvider.PREF_CLIENT_CODE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceDataModule {
    @Provides
    @Singleton
    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideRxSharedPreferences(prefs: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(prefs)
    }

    @ClientCode
    @Singleton
    @Provides
    internal fun provideClientCodePref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, PREF_CLIENT_CODE, "")
    }


    @LastScheduleDate
    @Singleton
    @Provides
    internal fun provideScheduleDatePref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, SCHEDULE_DATE_KEY, "")
    }

    @ImageSplashUrl
    @Singleton
    @Provides
    internal fun provideImageSplashPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, IMAGE_SPLASH_URL_KEY, "")
    }

    @LastBytesDate
    @Singleton
    @Provides
    internal fun provideBytesDatePref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, BYTES_DATE_KEY, "")
    }

    @RemoteConfigData
    @Singleton
    @Provides
    internal fun provideRemoteConfigDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, REMOTE_CONFIG_DATA_KEY, "")
    }

    @ScheduleData
    @Singleton
    @Provides
    internal fun provideScheduleDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, REMOTE_CONFIG_DATA_KEY, "")
    }

}
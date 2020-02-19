package com.mosl.mobile.coredata

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.Preference
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.mosl.annotations.FcmToken
import com.mosl.annotations.FcmTokenRequest
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.*
import com.mosl.mobile.app_user.*
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

    @LastNotificationDate
    @Singleton
    @Provides
    internal fun provideLastNotificationDatePref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, LAST_NOTIFICATION_DATE, "")
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
        return StringPreference(prefs, SCHEDULE_DATA_KEY, "")
    }

    @OverViewData
    @Singleton
    @Provides
    internal fun provideOverViewDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, OVERVIEW_DATA_KEY, "")
    }
    @NotificationData
    @Singleton
    @Provides
    internal fun provideNotificationDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, OVERVIEW_DATA_KEY, "")
    }

    @CorporateScheduleData
    @Singleton
    @Provides
    internal fun provideCorporateScheduleDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, CORPORATE_SCHEDULE_DATA_KEY, "")
    }
    @LastCorporateScheduleDate
    @Singleton
    @Provides
    internal fun provideCorporateScheduleDatePref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, CORPORATE_SCHEDULE_DATE_KEY, "")
    }

    @PanelData
    @Singleton
    @Provides
    internal fun providePanelDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, PANEL_DATA_KEY, "")
    }

    @LastBytesData
    @Singleton
    @Provides
    internal fun provideBytesDataPref(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, BYTES_DATA_KEY, "")
    }


    @FcmToken
    @Singleton
    @Provides
    internal fun provideGcmToken(prefs: RxSharedPreferences): Preference<String?> {
        return prefs.getString(GCM_TOKEN, "0")
    }

    @FcmTokenRequest
    @Singleton
    @Provides
    internal fun provideFcmTokenRequest(prefs: SharedPreferences): StringPreference {
        return StringPreference(prefs, SAVED_TOKEN_REQUEST)
    }
}
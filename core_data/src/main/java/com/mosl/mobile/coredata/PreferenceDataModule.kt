package com.mosl.mobile.coredata

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.ClientCode
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

}
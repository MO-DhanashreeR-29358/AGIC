package com.mosl.mobile.volunteer.coreui

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleManager {
    const val PREF_LANGUAGE = "MOSL_LANGUAGE"

    @JvmStatic
    fun withLocale(c: Context): Context {
        return updateResources(c, getLanguage(c))
    }

    @JvmStatic
    fun getLanguage(c: Context): String {
        val preferences = c.getSharedPreferences(c.packageName, Context.MODE_PRIVATE)
        return preferences.getString(PREF_LANGUAGE, "en") ?: "en"
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val config = Configuration(res.configuration)
            config.setLocale(locale)
            context.createConfigurationContext(config)
        } else {
            val config = res.configuration
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
            context
        }
    }
}

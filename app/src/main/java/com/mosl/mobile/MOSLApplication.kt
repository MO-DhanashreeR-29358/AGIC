package com.mosl.mobile

import androidx.appcompat.app.AppCompatDelegate
import com.mosl.app.core.utils.matchesService
import dagger.android.DaggerApplication

abstract class MOSLApplication : DaggerApplication() {
  lateinit var appComponent: ApplicationComponent

  init {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
  }

  override fun onCreate() {
    super.onCreate()

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    init()
  }

  abstract fun init()

  override fun getSystemService(name: String): Any? {
    return if (matchesService(name, ApplicationComponent::class.java)) {
      appComponent
    } else super.getSystemService(name)
  }

//  override fun onLowMemory() {
//    super.onLowMemory()
//    logCustomEvent(CustomEvent("LowMemory"))
//  }

}
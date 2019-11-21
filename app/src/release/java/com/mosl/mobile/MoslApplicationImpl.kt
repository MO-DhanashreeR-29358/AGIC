package com.mosl.mobile

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class MOSLApplicationImpl : MOSLApplication() {
  override fun init() {
//    Timber.plant(CrashlyticsTree())

    RxJavaPlugins.setErrorHandler { throwable -> Timber.tag("RxError").e(throwable) }

  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    val appComponent = DaggerApplicationComponent.builder().application(this).build()
    this.appComponent = appComponent
    return appComponent
  }
}
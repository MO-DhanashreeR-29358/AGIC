package com.mosl.mobile

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class MOSLApplicationImpl : MOSLApplication() {
  override fun init() {
//    Timber.plant(CrashlyticsTree())

//    Timber.plant(object : Timber.DebugTree() {
//      override fun createStackElementTag(element: StackTraceElement): String? {
//        return element.lineNumber.toString() + ":" + super.createStackElementTag(element)
//      }
//    })

    RxJavaPlugins.setErrorHandler { throwable -> Timber.tag("RxError").e(throwable) }

  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    val appComponent = DaggerApplicationComponent.builder().application(this).build()
    this.appComponent = appComponent
    return appComponent
  }
}
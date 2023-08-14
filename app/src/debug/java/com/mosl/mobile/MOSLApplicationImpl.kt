package com.mosl.mobile

import android.content.Context
import androidx.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mosl.app.core.utils.matchesService

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins

import timber.log.Timber
import javax.inject.Inject

class MOSLApplicationImpl : MOSLApplication() {
    @Inject
    lateinit var lumberYard: LumberYard

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build().also {
                this.appComponent = it
            }
    }

    override fun init() {
        //Add leak canary to debug builds.
//        LeakCanary.install(this)
//    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return element.lineNumber.toString() + ":" + super.createStackElementTag(element)
            }
        })

        AndroidThreeTen.init(this)
        RxJavaPlugins.setErrorHandler { throwable -> Timber.tag("RxError").e(throwable) }

        //Add stetho in debug builds for network inspection.
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
        // TODO SET CORE variable in fabric during PRODUCTION RELEASE
        //Disable crashlytics while keeping answers enabled on debug builds.
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        Fabric.with(this, Crashlytics(), core)

        lumberYard.cleanUp()
        Timber.plant(lumberYard.tree())
    }

    override fun getSystemService(name: String): Any? {
        return if (matchesService(name, DebugComponent::class.java)) {
            appComponent
        } else super.getSystemService(name)
    }
}
package com.mosl.mobile

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.app_user.user.UserModule
import com.mosl.mobile.splash.SplashActivity
import com.mosl.mobile.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UserModule::class])
abstract class CommonActivityBuilder {
  @ActivityScoped
  @ContributesAndroidInjector(modules = [SplashActivityModule::class])
  internal abstract fun bindSplashActivity(): SplashActivity
}
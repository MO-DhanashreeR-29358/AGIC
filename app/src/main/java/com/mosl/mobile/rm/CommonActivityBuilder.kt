package com.mosl.mobile.rm

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.appuser.user.UserModule
import com.mosl.mobile.rm.login.LoginActivity
import com.mosl.mobile.rm.login.LoginActivityModule
import com.mosl.mobile.rm.newsletter.NewsLetterActivity
import com.mosl.mobile.rm.newsletter.NewsLetterActivityModule
import com.mosl.mobile.rm.splash.SplashActivity
import com.mosl.mobile.rm.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UserModule::class])
abstract class CommonActivityBuilder {
  @ActivityScoped
  @ContributesAndroidInjector(modules = [SplashActivityModule::class])
  internal abstract fun bindSplashActivity(): SplashActivity

  @ActivityScoped
  @ContributesAndroidInjector(modules = [LoginActivityModule::class])
  internal abstract fun bindLoginActivity(): LoginActivity

  @ActivityScoped
  @ContributesAndroidInjector(modules = [NewsLetterActivityModule::class])
  internal abstract fun bindNewsLetterActivity(): NewsLetterActivity



}
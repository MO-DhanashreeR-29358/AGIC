package com.mosl.mobile

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.app_user.user.UserModule
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.login.LoginActivityModule
import com.mosl.mobile.newsletter.NewsLetterActivity
import com.mosl.mobile.newsletter.NewsLetterActivityModule
import com.mosl.mobile.schedule.ScheduleFragment
import com.mosl.mobile.schedule.ScheduleModule
import com.mosl.mobile.splash.SplashActivity
import com.mosl.mobile.splash.SplashActivityModule
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
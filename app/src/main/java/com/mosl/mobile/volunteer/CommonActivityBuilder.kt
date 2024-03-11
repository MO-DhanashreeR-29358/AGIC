package com.mosl.mobile.volunteer

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.volunteer.appuser.user.UserModule
import com.mosl.mobile.volunteer.login.LoginActivity
import com.mosl.mobile.volunteer.login.LoginActivityModule
import com.mosl.mobile.volunteer.newsletter.NewsLetterActivity
import com.mosl.mobile.volunteer.newsletter.NewsLetterActivityModule
import com.mosl.mobile.volunteer.splash.SplashActivity
import com.mosl.mobile.volunteer.splash.SplashActivityModule
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
package com.mosl.volunteer

import com.mosl.annotations.ActivityScoped
import com.mosl.volunteer.appuser.user.UserModule
import com.mosl.volunteer.login.LoginActivity
import com.mosl.volunteer.login.LoginActivityModule
import com.mosl.volunteer.newsletter.NewsLetterActivity
import com.mosl.volunteer.newsletter.NewsLetterActivityModule
import com.mosl.volunteer.splash.SplashActivity
import com.mosl.volunteer.splash.SplashActivityModule
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
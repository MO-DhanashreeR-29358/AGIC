package com.mosl.volunteer.menu

import com.mosl.volunteer.helper.CustomNavigator
import com.mosl.volunteer.helper.CustomNavigatorImpl
import com.mosl.navigator.BaseNavigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorAliceProvider {
  @Binds
  abstract fun provideCustomNavigator(urlNavigator: CustomNavigatorImpl): CustomNavigator

  @Binds
  abstract fun provideScreenNavigator(screenNavigator: ScreenNavigatorImpl): ScreenNavigator

  @Binds
  abstract fun provideBaseNavigator(screenNavigator: ScreenNavigatorImpl): BaseNavigator
}
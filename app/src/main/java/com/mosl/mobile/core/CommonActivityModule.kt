package com.mosl.mobile.core

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.mosl.app.coreui.base.ActivityDelegate
import com.mosl.mobile.FragmentProvider
import com.mosl.mobile.menu.NavigatorAliceProvider
import com.mosl.mobile.util.NavigationCallbackImpl
import com.mosl.navigator.FragmentTransitionHelper
import com.mosl.navigator.FragmentTransitionHelperImpl
import com.mosl.navigator.NavigationCallback
import com.mosl.navigator.Navigator
import dagger.Binds
import dagger.Module

@Module(includes = [FragmentProvider::class, NavigatorAliceProvider::class])
abstract class CommonActivityModule {
    @Binds
    internal abstract fun provideNavigationCallback(
        navigationCallback: NavigationCallbackImpl
    ): NavigationCallback

    @Binds
    internal abstract fun provideFragmentTransitionHelper(
        transitionHelper: FragmentTransitionHelperImpl
    ): FragmentTransitionHelper

    @Binds
    internal abstract fun provideFragmentActivity(activity: MOSLCommonActivity): FragmentActivity

    @Binds
    internal abstract fun provideAppCompatActivity(activity: MOSLCommonActivity): AppCompatActivity

    @Binds
    internal abstract fun provideNavigator(activity: MOSLCommonActivity): Navigator

    @Binds
    internal abstract fun provideActivityDelegate(activity: MOSLCommonActivity): ActivityDelegate


}
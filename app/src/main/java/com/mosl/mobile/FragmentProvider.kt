package com.mosl.mobile

import com.mosl.mobile.contactrm.ContactDialogFrafment
import com.mosl.mobile.demo.DemoFragmentProvider
import com.mosl.mobile.panel.PanelFragment
import com.mosl.mobile.panel.PanelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DemoFragmentProvider::class])
abstract class FragmentProvider {


    @ContributesAndroidInjector(modules = [])
    abstract fun provideContactDialogFragmentFactory(): ContactDialogFrafment
}
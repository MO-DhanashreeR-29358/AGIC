package com.mosl.mobile

import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.bytes.BytesModule
import com.mosl.mobile.panelbase.PanelBaseFragment
import com.mosl.mobile.panelbase.PanelBaseModule
import com.mosl.mobile.portfolio.PortfolioFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PortfolioFragmentProvider::class])
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [BytesModule::class])
    abstract fun provideBytesFragmentFactory(): BytesFragment

    @ContributesAndroidInjector(modules = [PanelBaseModule::class])
    abstract fun providePanelBAseFragmentFactory(): PanelBaseFragment

}
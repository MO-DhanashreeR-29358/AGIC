package com.mosl.mobile

import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.bytes.BytesModule
import com.mosl.mobile.overview.OverviewFragment
import com.mosl.mobile.overview.OverviewModule
import com.mosl.mobile.panelpager.PanelPagerFragment
import com.mosl.mobile.panelpager.PanelPagerModule
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.mobile.pdf.PdfModule
import com.mosl.mobile.portfolio.PortfolioFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [PortfolioFragmentProvider::class])
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [BytesModule::class])
    abstract fun provideBytesFragmentFactory(): BytesFragment

    @ContributesAndroidInjector(modules = [PanelPagerModule::class])
    abstract fun providePanelBAseFragmentFactory(): PanelPagerFragment

    @ContributesAndroidInjector(modules = [OverviewModule::class])
    abstract fun provideOverviewFragmentFactory(): OverviewFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun providePDFFragmentFactory(): PdfFragment

}
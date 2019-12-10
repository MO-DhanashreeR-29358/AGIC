package com.mosl.mobile

import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.bytes.BytesModule
import com.mosl.mobile.contactrm.ContactDialogFragment
import com.mosl.mobile.contactrm.ContactModule
import com.mosl.mobile.demo.DemoFragmentProvider
import com.mosl.mobile.notes.NotesFragment
import com.mosl.mobile.notes.NotesModule
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.overview.OverviewChildModule
import com.mosl.mobile.overview.OverviewRepDialogFragment
import com.mosl.mobile.overviewpager.OverviewFragment
import com.mosl.mobile.overviewpager.OverviewPagerModule
import com.mosl.mobile.panel.PanelChildFragment
import com.mosl.mobile.panel.PanelChildModule
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelModule
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.mobile.schedule.ScheduleFragment
import com.mosl.mobile.schedule.ScheduleModule
import com.mosl.mobile.schedule.ScheduleRepDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DemoFragmentProvider::class])
abstract class FragmentProvider {

    @ContributesAndroidInjector(modules = [])
    abstract fun provideScheduleRepDialogFragmentFactory(): ScheduleRepDialogFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun provideOverviewRepDialogFragmentFragmentFactory(): OverviewRepDialogFragment

    @ContributesAndroidInjector(modules = [ContactModule::class])
    abstract fun provideContactDialogFragmentFactory(): ContactDialogFragment

    @ContributesAndroidInjector(modules = [BytesModule::class])
    abstract fun provideBytesFragmentFactory(): BytesFragment

    @ContributesAndroidInjector(modules = [PanelModule::class])
    abstract fun providePanelBAseFragmentFactory(): PanelFragment

    @ContributesAndroidInjector(modules = [PanelChildModule::class])
    abstract fun providePanelFragmentFactory(): PanelChildFragment

    @ContributesAndroidInjector(modules = [OverviewChildModule::class])
    abstract fun provideOverviewFragmentChildFactory(): OverviewChildFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun providePDFFragmentFactory(): PdfFragment


    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun provideNotesFragmentFactory(): NotesFragment

    @ContributesAndroidInjector(modules = [ScheduleModule::class])
    abstract fun provideScheduleFragmentFactory(): ScheduleFragment

    @ContributesAndroidInjector(modules = [OverviewPagerModule::class])
    abstract fun provideOverviewFragmentFactory(): OverviewFragment

}
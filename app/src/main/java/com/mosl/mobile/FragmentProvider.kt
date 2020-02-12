package com.mosl.mobile

import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.bytes.BytesModule
import com.mosl.mobile.contactrm.ContactDialogFragment
import com.mosl.mobile.contactrm.ContactModule
import com.mosl.mobile.corporatepager.CorporatePagerFragment
import com.mosl.mobile.corporatepager.CorporatePagerModule
import com.mosl.mobile.corporateschedule.CorporateScheduleChildModule
import com.mosl.mobile.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.corporateschedule.CorporateScheduleRepDialogFragment
import com.mosl.mobile.demo.DemoFragmentProvider
import com.mosl.mobile.notes.NotesFragment
import com.mosl.mobile.notes.NotesModule
import com.mosl.mobile.notification.NotificationHistoryFragment
import com.mosl.mobile.notification.NotificationModule
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
interface FragmentProvider {

    @ContributesAndroidInjector(modules = [])
    fun provideScheduleRepDialogFragmentFactory(): ScheduleRepDialogFragment

    @ContributesAndroidInjector(modules = [])
    fun provideOverviewRepDialogFragmentFragmentFactory(): OverviewRepDialogFragment

    @ContributesAndroidInjector(modules = [ContactModule::class])
    fun provideContactDialogFragmentFactory(): ContactDialogFragment

    @ContributesAndroidInjector(modules = [BytesModule::class])
    fun provideBytesFragmentFactory(): BytesFragment

    @ContributesAndroidInjector(modules = [PanelModule::class])
    fun providePanelBAseFragmentFactory(): PanelFragment

    @ContributesAndroidInjector(modules = [PanelChildModule::class])
    fun providePanelFragmentFactory(): PanelChildFragment

    @ContributesAndroidInjector(modules = [OverviewChildModule::class])
    fun provideOverviewFragmentChildFactory(): OverviewChildFragment

    @ContributesAndroidInjector(modules = [])
    fun providePDFFragmentFactory(): PdfFragment


    @ContributesAndroidInjector(modules = [NotesModule::class])
    fun provideNotesFragmentFactory(): NotesFragment

    @ContributesAndroidInjector(modules = [ScheduleModule::class])
    fun provideScheduleFragmentFactory(): ScheduleFragment

    @ContributesAndroidInjector(modules = [OverviewPagerModule::class])
    fun provideOverviewFragmentFactory(): OverviewFragment

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    fun provideNotificationHistoryFactory(): NotificationHistoryFragment

    @ContributesAndroidInjector(modules = [CorporatePagerModule::class])
    fun provideCorporatePagerFragment(): CorporatePagerFragment

    @ContributesAndroidInjector(modules = [CorporateScheduleChildModule::class])
    fun provideCorporateScheduleFragmentFactory(): CorporateScheduleFragment

    @ContributesAndroidInjector(modules = [])
    fun provideCorporateScheduleRepDialogFragmentFragmentFactory(): CorporateScheduleRepDialogFragment
}
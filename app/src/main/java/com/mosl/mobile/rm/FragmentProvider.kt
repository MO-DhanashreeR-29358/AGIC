package com.mosl.mobile.rm

import com.mosl.mobile.rm.bytes.BytesFragment
import com.mosl.mobile.rm.bytes.BytesModule
import com.mosl.mobile.rm.contactrm.ContactDialogFragment
import com.mosl.mobile.rm.contactrm.ContactModule
import com.mosl.mobile.rm.corporatepager.CorporatePagerFragment
import com.mosl.mobile.rm.corporatepager.CorporatePagerModule
import com.mosl.mobile.rm.corporateschedule.CorporateScheduleChildModule
import com.mosl.mobile.rm.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.rm.corporateschedule.CorporateScheduleRepDialogFragment
import com.mosl.mobile.rm.demo.DemoFragmentProvider
import com.mosl.mobile.rm.feedback.FeedbackFragment
import com.mosl.mobile.rm.feedback.FeedbackModule
import com.mosl.mobile.rm.notes.NotesFragment
import com.mosl.mobile.rm.notes.NotesModule
import com.mosl.mobile.rm.notification.NotificationHistoryFragment
import com.mosl.mobile.rm.notification.NotificationModule
import com.mosl.mobile.rm.notification.NotificationRepsDialogFragment
import com.mosl.mobile.rm.overview.OverviewChildFragment
import com.mosl.mobile.rm.overview.OverviewChildModule
import com.mosl.mobile.rm.overview.OverviewRepDialogFragment
import com.mosl.mobile.rm.overviewpager.OverviewFragment
import com.mosl.mobile.rm.overviewpager.OverviewPagerModule
import com.mosl.mobile.rm.panel.PanelChildFragment
import com.mosl.mobile.rm.panel.PanelChildModule
import com.mosl.mobile.rm.panelpager.PanelFragment
import com.mosl.mobile.rm.panelpager.PanelModule
import com.mosl.mobile.rm.pdf.PdfFragment
import com.mosl.mobile.rm.rmclientwise.RmClientWiseFragment
import com.mosl.mobile.rm.rmclientwise.RmClientWiseModule
import com.mosl.mobile.rm.rmclientwisepager.RmClientOverviewFragment
import com.mosl.mobile.rm.rmclientwisepager.RmClientOverviewPagerModule
import com.mosl.mobile.rm.schedule.ScheduleFragment
import com.mosl.mobile.rm.schedule.ScheduleModule
import com.mosl.mobile.rm.schedule.ScheduleRepDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DemoFragmentProvider::class])
interface FragmentProvider {

    @ContributesAndroidInjector(modules = [])
    fun provideScheduleRepDialogFragmentFactory(): ScheduleRepDialogFragment

    @ContributesAndroidInjector(modules = [])
    fun provideNotificationRepsDialogFragmentFactory(): NotificationRepsDialogFragment

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

    @ContributesAndroidInjector(modules = [RmClientWiseModule::class])
    fun provideRmChildWiseFragmentFactory(): RmClientWiseFragment


    @ContributesAndroidInjector(modules = [])
    fun providePDFFragmentFactory(): PdfFragment


    @ContributesAndroidInjector(modules = [NotesModule::class])
    fun provideNotesFragmentFactory(): NotesFragment

    @ContributesAndroidInjector(modules = [ScheduleModule::class])
    fun provideScheduleFragmentFactory(): ScheduleFragment

    @ContributesAndroidInjector(modules = [OverviewPagerModule::class])
    fun provideOverviewFragmentFactory(): OverviewFragment

    @ContributesAndroidInjector(modules = [RmClientOverviewPagerModule::class])
    fun provideRmClientOverviewFragmentFactory(): RmClientOverviewFragment

    @ContributesAndroidInjector(modules = [NotificationModule::class])
    fun provideNotificationHistoryFactory(): NotificationHistoryFragment

    @ContributesAndroidInjector(modules = [FeedbackModule::class])
    fun provideFeedbackFactory():FeedbackFragment

    @ContributesAndroidInjector(modules = [CorporatePagerModule::class])
    fun provideCorporatePagerFragment(): CorporatePagerFragment

    @ContributesAndroidInjector(modules = [CorporateScheduleChildModule::class])
    fun provideCorporateScheduleFragmentFactory(): CorporateScheduleFragment

    @ContributesAndroidInjector(modules = [])
    fun provideCorporateScheduleRepDialogFragmentFragmentFactory(): CorporateScheduleRepDialogFragment
}
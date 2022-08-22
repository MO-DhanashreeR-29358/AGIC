package com.mosl.volunteer

import com.mosl.volunteer.bytes.BytesFragment
import com.mosl.volunteer.bytes.BytesModule
import com.mosl.volunteer.contactrm.ContactDialogFragment
import com.mosl.volunteer.contactrm.ContactModule
import com.mosl.volunteer.corporatepager.CorporatePagerFragment
import com.mosl.volunteer.corporatepager.CorporatePagerModule
import com.mosl.volunteer.corporateschedule.CorporateScheduleChildModule
import com.mosl.volunteer.corporateschedule.CorporateScheduleFragment
import com.mosl.volunteer.corporateschedule.CorporateScheduleRepDialogFragment
import com.mosl.volunteer.demo.DemoFragmentProvider
import com.mosl.volunteer.feedback.FeedbackFragment
import com.mosl.volunteer.feedback.FeedbackModule
import com.mosl.volunteer.notes.NotesFragment
import com.mosl.volunteer.notes.NotesModule
import com.mosl.volunteer.notification.NotificationHistoryFragment
import com.mosl.volunteer.notification.NotificationModule
import com.mosl.volunteer.notification.NotificationRepsDialogFragment
import com.mosl.volunteer.overview.OverviewChildFragment
import com.mosl.volunteer.overview.OverviewChildModule
import com.mosl.volunteer.overview.OverviewRepDialogFragment
import com.mosl.volunteer.overviewpager.OverviewFragment
import com.mosl.volunteer.overviewpager.OverviewPagerModule
import com.mosl.volunteer.panel.PanelChildFragment
import com.mosl.volunteer.panel.PanelChildModule
import com.mosl.volunteer.panelpager.PanelFragment
import com.mosl.volunteer.panelpager.PanelModule
import com.mosl.volunteer.pdf.PdfFragment
import com.mosl.volunteer.rmclientwise.RmClientWiseFragment
import com.mosl.volunteer.rmclientwise.RmClientWiseModule
import com.mosl.volunteer.rmclientwisepager.RmClientOverviewFragment
import com.mosl.volunteer.rmclientwisepager.RmClientOverviewPagerModule
import com.mosl.volunteer.schedule.ScheduleFragment
import com.mosl.volunteer.schedule.ScheduleModule
import com.mosl.volunteer.schedule.ScheduleRepDialogFragment
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
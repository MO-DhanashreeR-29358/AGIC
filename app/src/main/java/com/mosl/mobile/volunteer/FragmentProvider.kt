package com.mosl.mobile.volunteer

import com.mosl.mobile.volunteer.attendance.attendancedetails.AttendanceDetailsFragment
import com.mosl.mobile.volunteer.attendance.attendancedetails.AttendanceDetailsModule
import com.mosl.mobile.volunteer.bytes.BytesFragment
import com.mosl.mobile.volunteer.bytes.BytesModule
import com.mosl.mobile.volunteer.contactrm.ContactDialogFragment
import com.mosl.mobile.volunteer.contactrm.ContactModule
import com.mosl.mobile.volunteer.corporatepager.CorporatePagerFragment
import com.mosl.mobile.volunteer.corporatepager.CorporatePagerModule
import com.mosl.mobile.volunteer.attendance.corporateqr.CorporateNamesListFragment
import com.mosl.mobile.volunteer.attendance.corporateqr.CorporateNamesListModule
import com.mosl.mobile.volunteer.attendance.corporateqr.QrCodeScanFragment
import com.mosl.mobile.volunteer.attendance.corporateqr.QrCodeScanModule
import com.mosl.mobile.volunteer.corporateschedule.CorporateScheduleChildModule
import com.mosl.mobile.volunteer.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.volunteer.corporateschedule.CorporateScheduleRepDialogFragment
import com.mosl.mobile.volunteer.demo.DemoFragmentProvider
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import com.mosl.mobile.volunteer.feedback.FeedbackFragment
import com.mosl.mobile.volunteer.feedback.FeedbackModule
import com.mosl.mobile.volunteer.notes.NotesFragment
import com.mosl.mobile.volunteer.notes.NotesModule
import com.mosl.mobile.volunteer.notification.NotificationHistoryFragment
import com.mosl.mobile.volunteer.notification.NotificationModule
import com.mosl.mobile.volunteer.notification.NotificationRepsDialogFragment
import com.mosl.mobile.volunteer.overview.OverviewChildFragment
import com.mosl.mobile.volunteer.overview.OverviewChildModule
import com.mosl.mobile.volunteer.overview.OverviewRepDialogFragment
import com.mosl.mobile.volunteer.overviewpager.OverviewFragment
import com.mosl.mobile.volunteer.overviewpager.OverviewPagerModule
import com.mosl.mobile.volunteer.panel.PanelChildFragment
import com.mosl.mobile.volunteer.panel.PanelChildModule
import com.mosl.mobile.volunteer.panelpager.PanelFragment
import com.mosl.mobile.volunteer.panelpager.PanelModule
import com.mosl.mobile.volunteer.pdf.PdfFragment
import com.mosl.mobile.volunteer.rmclientwise.RmClientWiseFragment
import com.mosl.mobile.volunteer.rmclientwise.RmClientWiseModule
import com.mosl.mobile.volunteer.rmclientwisepager.RmClientOverviewFragment
import com.mosl.mobile.volunteer.rmclientwisepager.RmClientOverviewPagerModule
import com.mosl.mobile.volunteer.schedule.ScheduleFragment
import com.mosl.mobile.volunteer.schedule.ScheduleModule
import com.mosl.mobile.volunteer.schedule.ScheduleRepDialogFragment
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

    @ContributesAndroidInjector(modules = [CorporateNamesListModule::class])
    fun provideCorporateNamesListFragmentFactory(): CorporateNamesListFragment

    @ContributesAndroidInjector(modules = [QrCodeScanModule::class])
    fun provideQrCodeScanFragmentFactory(): QrCodeScanFragment

    @ContributesAndroidInjector(modules = [AttendanceDetailsModule::class])
    fun provideAttendanceDetailsFragmentFactory(): AttendanceDetailsFragment
}
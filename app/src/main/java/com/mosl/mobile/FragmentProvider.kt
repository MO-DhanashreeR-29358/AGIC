package com.mosl.mobile

import com.mosl.mobile.contactrm.ContactDialogFragment
import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.bytes.BytesModule
import com.mosl.mobile.contactrm.ContactModule
import com.mosl.mobile.demo.DemoFragmentProvider
import com.mosl.mobile.notes.NotesFragment
import com.mosl.mobile.notes.NotesModule
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.overview.OverviewChildModule
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelModule
import com.mosl.mobile.pdf.PdfFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DemoFragmentProvider::class])
abstract class FragmentProvider {


    @ContributesAndroidInjector(modules = [ContactModule::class])
    abstract fun provideContactDialogFragmentFactory(): ContactDialogFragment

    @ContributesAndroidInjector(modules = [BytesModule::class])
    abstract fun provideBytesFragmentFactory(): BytesFragment

    @ContributesAndroidInjector(modules = [PanelModule::class])
    abstract fun providePanelBAseFragmentFactory(): PanelFragment

    @ContributesAndroidInjector(modules = [OverviewChildModule::class])
    abstract fun provideOverviewFragmentFactory(): OverviewChildFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun providePDFFragmentFactory(): PdfFragment


    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun provideNotesFragmentFactory(): NotesFragment

}
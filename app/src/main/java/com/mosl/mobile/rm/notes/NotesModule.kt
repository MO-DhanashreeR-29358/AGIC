package com.mosl.mobile.rm.notes

import dagger.Module
import dagger.Provides

@Module
class NotesModule {
    @Provides
    internal fun ProvideNotesFragment(fragment: NotesFragment): NotesContract {
        return fragment
    }
}
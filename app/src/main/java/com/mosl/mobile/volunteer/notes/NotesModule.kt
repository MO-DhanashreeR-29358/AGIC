package com.mosl.mobile.volunteer.notes

import dagger.Module
import dagger.Provides

@Module
class NotesModule {
    @Provides
    internal fun ProvideNotesFragment(fragment: NotesFragment): NotesContract {
        return fragment
    }
}
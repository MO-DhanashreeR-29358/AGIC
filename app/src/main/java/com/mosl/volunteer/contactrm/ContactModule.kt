package com.mosl.volunteer.contactrm

import dagger.Module
import dagger.Provides

@Module
class ContactModule {

    @Provides
    internal fun ProvideContactDialogFragment(fragment: ContactDialogFragment): ContactView {
        return fragment
    }
}
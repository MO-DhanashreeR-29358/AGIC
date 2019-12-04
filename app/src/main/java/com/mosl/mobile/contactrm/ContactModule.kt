package com.mosl.mobile.contactrm

import dagger.Module
import dagger.Provides

@Module
class ContactModule {

    @Provides
    internal fun ProvideContactDialogFragment(fragment: ContactDialogFragment): ContactView {
        return fragment
    }
}
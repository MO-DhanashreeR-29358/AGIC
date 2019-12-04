package com.mosl.mobile.contactrm

import com.mosl.app.core.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ContactDialogPresenter @Inject constructor(
    view: ContactView
) : BasePresenter<ContactView>(view) {
    override fun start() {

        view.onCallClicked().subscribe {
            it.mobileNo?.apply {
                view.showAlertForCall(this)
            }

        }.addTo(disposable)

    }
}
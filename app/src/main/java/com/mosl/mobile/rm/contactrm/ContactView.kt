package com.mosl.mobile.rm.contactrm

import com.mosl.app.core.base.BaseView
import io.reactivex.Observable

interface ContactView : BaseView {

        fun showAlertForCall(mobileNumber:String)
       fun onCallClicked(): Observable<CallUiEvent.CallClick>
}
package com.mosl.mobile.rm.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<com.mosl.mobile.appuser.model.RmUser>)
  fun getUniqueCode(): String
  fun getDeviceId(): String
  fun onDoneClicked(): Observable<Int>
}
package com.mosl.volunteer.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<com.mosl.volunteer.appuser.model.RmUser>)
  fun getUniqueCode(): String
  fun getDeviceId(): String
  fun onDoneClicked(): Observable<Int>
}
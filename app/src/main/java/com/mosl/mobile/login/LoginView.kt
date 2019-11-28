package com.mosl.mobile.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<com.mosl.mobile.app_user.model.User>)
  fun getUniqueCode(): String
  fun getDeviceId(): String
  fun onDoneClicked(): Observable<Int>
}
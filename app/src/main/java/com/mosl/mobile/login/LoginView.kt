package com.mosl.mobile.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.app_user.model.LoginResponse
import com.mosl.mobile.demo.User
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<LoginResponse>)
  fun getUniqueCode(): String
  fun getDeviceId(): String
  fun onDoneClicked(): Observable<Int>
}
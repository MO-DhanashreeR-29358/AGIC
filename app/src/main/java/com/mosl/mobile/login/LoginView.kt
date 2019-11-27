package com.mosl.mobile.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.demo.User
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<Unit>)
}
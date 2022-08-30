package com.mosl.mobile.volunteer.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<com.mosl.mobile.volunteer.appuser.model.RmUser>)
  fun getUserId(): String
  fun getTodaysDate(): String
  fun onDoneClicked(): Observable<Int>
}
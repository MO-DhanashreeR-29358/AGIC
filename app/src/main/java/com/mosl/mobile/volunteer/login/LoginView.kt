package com.mosl.mobile.volunteer.login

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import io.reactivex.Observable

interface LoginView : BaseView {
  fun onLoginClicked(): Observable<Unit>
  fun renderLogin(uiModel: UiModel<Volunteer>)
  fun getUserId(): String
  fun getTodaysDate(): String
  fun getPassword(): String
  fun onDoneClicked(): Observable<Int>
}
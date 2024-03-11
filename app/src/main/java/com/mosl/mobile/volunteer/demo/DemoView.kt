package com.mosl.mobile.volunteer.demo

import androidx.lifecycle.LiveData
import com.mosl.app.core.base.BaseView

interface DemoView : BaseView {
  fun onStarted()
  fun onSuccess(user: LiveData<UserResponseMapper>)
  fun onError(message: String)
  fun onFailure(message: String)
}
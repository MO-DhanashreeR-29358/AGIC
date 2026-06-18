package com.mosl.mobile.rm.demo

import androidx.lifecycle.LiveData
import com.mosl.app.core.base.BaseView
import com.mosl.mobile.demo.UserResponseMapper

interface DemoView : BaseView {
  fun onStarted()
  fun onSuccess(user: LiveData<UserResponseMapper>)
  fun onError(message: String)
  fun onFailure(message: String)
}
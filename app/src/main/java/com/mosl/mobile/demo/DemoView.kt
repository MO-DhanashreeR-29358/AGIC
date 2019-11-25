package com.mosl.mobile.demo

import androidx.lifecycle.LiveData
import com.mosl.app.core.base.BaseView

interface DemoView:BaseView {
  fun onStarted()
  fun onSuccess(user: LiveData<AuthResponseM>)
  fun onFailure(message: String)
}
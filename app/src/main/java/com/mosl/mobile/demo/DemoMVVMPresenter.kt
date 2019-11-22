package com.mosl.mobile.demo

import com.mosl.app.core.base.BasePresenter
import javax.inject.Inject

class DemoMVVMPresenter @Inject constructor(view: DemoMVVMView):BasePresenter<DemoMVVMView>(view) {
  override fun start() {
  }
}
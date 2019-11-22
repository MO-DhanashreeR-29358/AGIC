package com.mosl.mobile.demo

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import javax.inject.Inject

class DemoMVVMFragment : MOSLCommonFragment(), DemoMVVMView {
  @Inject
  lateinit var presenter: DemoMVVMPresenter

  override fun getLayout(): Int {
    return R.layout.fragment_demo_mvvm
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.start()
  }

  override fun onDestroyView() {
    presenter.stop()
    super.onDestroyView()
  }
}
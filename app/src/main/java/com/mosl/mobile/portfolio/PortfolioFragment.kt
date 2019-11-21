package com.mosl.mobile.portfolio

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import javax.inject.Inject

class PortfolioFragment : MOSLCommonFragment(), PortfolioView {
  @Inject
  lateinit var presenter: PortfolioPresenter

  override fun getLayout(): Int {
    return R.layout.fragment_portfolio
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
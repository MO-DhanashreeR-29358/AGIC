package com.mosl.app.coreui.adapters

import com.mosl.app.coreui.base.BaseFragment

interface PagerFragmentProvider {
  fun getFragmentAt(position: Int): BaseFragment?
}
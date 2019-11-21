package com.mosl.mobile.featurescope

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.Fragment2Binding

class SecondPlaygroundFragment : MOSLCommonFragment() {
  lateinit var binding: Fragment2Binding

  override fun getLayout(): Int {
    return R.layout.fragment2
  }

  override fun onViewCreated(
      view: View,
      savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding = Fragment2Binding.bind(view)
  }
}
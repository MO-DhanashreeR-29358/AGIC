package com.mosl.volunteer.featurescope

import android.os.Bundle
import android.view.View
import com.mosl.volunteer.R
import com.mosl.volunteer.core.MOSLCommonFragment
import com.mosl.volunteer.databinding.Fragment2Binding

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
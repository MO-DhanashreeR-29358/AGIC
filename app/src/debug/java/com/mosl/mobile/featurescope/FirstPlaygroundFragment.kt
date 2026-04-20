package com.mosl.mobile.featurescope

import android.os.Bundle
import android.view.View
import com.mosl.data.api.AGICApi
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentFeatureMainBinding
import javax.inject.Inject

class FirstPlaygroundFragment : MOSLCommonFragment() {
  @Inject
  lateinit var moslApi: AGICApi
  lateinit var binding: FragmentFeatureMainBinding

  override fun getLayout(): Int {
    return R.layout.fragment_feature_main
  }

  override fun onViewCreated(
      view: View,
      savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentFeatureMainBinding.bind(view)
  }
}



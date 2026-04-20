package com.mosl.mobile.drawermenu

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentDrawerMenuBinding

/**
 * Created by lalkishore on 08/02/18.
 */

class DrawerMenuFragment : MOSLCommonFragment() {
  lateinit var binding: FragmentDrawerMenuBinding

  override fun getLayout(): Int {
    return R.layout.fragment_drawer_menu
  }

  override fun onViewCreated(
      view: View,
      savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentDrawerMenuBinding.bind(view)
  }

}

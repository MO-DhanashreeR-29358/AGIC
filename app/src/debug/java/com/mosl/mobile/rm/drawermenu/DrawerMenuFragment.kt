package com.mosl.mobile.rm.drawermenu

import android.os.Bundle
import android.view.View
import com.mosl.mobile.rm.R
import com.mosl.mobile.rm.core.MOSLCommonFragment
import com.mosl.mobile.rm.databinding.FragmentDrawerMenuBinding

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

package com.mosl.volunteer.drawermenu

import android.os.Bundle
import android.view.View
import com.mosl.volunteer.R
import com.mosl.volunteer.core.MOSLCommonFragment
import com.mosl.volunteer.databinding.FragmentDrawerMenuBinding

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

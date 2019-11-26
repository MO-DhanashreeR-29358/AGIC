package com.mosl.mobile.login

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentLoginBinding
import com.mosl.mobile.nav.DebugConstants

class LoginFragment : MOSLCommonFragment() {

    lateinit var binding: FragmentLoginBinding


    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.btnlogin.setOnClickListener(View.OnClickListener { openDashboard() })
    }

    internal fun openDashboard() {
        val bundle = Bundle()
        navigateTo(DebugConstants.NAVIGATE_TO_OVERVIEW, bundle, true)
    }

}

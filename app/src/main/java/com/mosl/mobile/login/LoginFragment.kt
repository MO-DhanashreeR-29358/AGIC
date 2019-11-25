package com.mosl.mobile.login

import android.os.Bundle
import android.view.View
import com.mosl.app.coreui.base.BaseFragment
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentLoginBinding

class LoginFragment : MOSLCommonFragment() {

    lateinit var binding: FragmentLoginBinding


    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
    }
}

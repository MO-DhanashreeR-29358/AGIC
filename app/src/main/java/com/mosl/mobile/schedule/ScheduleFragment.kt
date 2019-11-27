package com.mosl.mobile.schedule

import android.os.Bundle
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.databinding.FragmentScheduleBinding

class ScheduleFragment : MOSLCommonFragment() {

    lateinit var binding: FragmentScheduleBinding


    override fun getLayout(): Int {
        return R.layout.fragment_schedule
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)

    }
}

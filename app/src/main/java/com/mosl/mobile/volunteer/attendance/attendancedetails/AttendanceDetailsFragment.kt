package com.mosl.mobile.volunteer.attendance.attendancedetails

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.RemoteConfigData
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceCorporateListBinding
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceDetailsBinding
import javax.inject.Inject

class AttendanceDetailsFragment: MOSLCommonFragment(), AttendanceDetailsContract {
    private lateinit var binding: FragmentAttendanceDetailsBinding
    private var remoteConfigModel: RemoteConfigModel? = null

    @Inject
    lateinit var userManager: UserManager

    @Inject
    @field:RemoteConfigData
    lateinit var remoteConfigData: StringPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_attendance_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceDetailsBinding.bind(view)
        remoteConfigModel =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        setUpViews()
    }

    private fun setUpViews() {
        binding



        Glide.with(this)
            .load(remoteConfigModel?.logo)
            .into(binding.header.icConfLogo)
    }
}
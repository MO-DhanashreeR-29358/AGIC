package com.mosl.mobile.volunteer.attendance.attendancedetails

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.mosl.app.core.prefs.StringPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.commons.rx.addTo
import com.mosl.commons.rx.ofType
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.RemoteConfigData
import com.mosl.mobile.volunteer.appuser.login.UserManagerImpl
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceDetailsBinding
import com.mosl.shared.AppDateFormats
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AttendanceDetailsFragment: MOSLCommonFragment(), AttendanceDetailsContract {
    private lateinit var binding: FragmentAttendanceDetailsBinding
    private var remoteConfigModel: RemoteConfigModel? = null

    private val subject = PublishSubject.create<AttendanceDetailsEvent>()

    @Inject
    lateinit var userManager: UserManagerImpl

    @Inject
    @field:RemoteConfigData
    lateinit var remoteConfigData: StringPreference

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var presenter: AttendanceDetailsPresenter

    override fun getLayout(): Int {
        return R.layout.fragment_attendance_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceDetailsBinding.bind(view)
        remoteConfigModel =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        setUpViews()

        binding.btYes.clicks()
            .subscribe { subject.onNext(AttendanceDetailsEvent.YesNoClicked(isAllowed = 1)) }
            .addTo(disposable)

        binding.btNo.clicks()
            .subscribe { subject.onNext(AttendanceDetailsEvent.YesNoClicked(isAllowed = 0)) }
            .addTo(disposable)

        presenter.start()
    }

    override fun onYesNoBtnClicked(): Observable<AttendanceDetailsEvent.YesNoClicked> {
        return subject.ofType()
    }

    override fun renderAttendanceMarked(uiModel: UiModel<String>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            showOnFailurePopup(it)
        }
        uiModel.onSuccess {
            screenNavigator.openHome()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    private fun setUpViews() {
        val clientModel = userManager.getClientModel()
        binding.apply {
            tvCorporateName.text = clientModel?.companyName.orEmpty()
            btFundName.text = clientModel?.fundName.orEmpty()
            btClientName.text = clientModel?.clientName.orEmpty()
            btStatus.text = clientModel?.status.orEmpty()
            if (btStatus.text == getString(R.string.bt_status_registered_label)) {
                btStatus.setText(R.color._green)
            }
        }
        setTimeSlot(clientModel?.startTime, clientModel?.endTime)
        Glide.with(this)
            .load(remoteConfigModel?.logo)
            .into(binding.header.icConfLogo)
    }

    private fun setTimeSlot(startTimeString: String?, endTimeString: String?) {
        if (startTimeString.isNullOrEmpty() || endTimeString.isNullOrEmpty()) {
            binding.btSlot.text = getString(R.string.bt_time_slot_na_label)
            binding.btSlot.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_error))
            return
        }

        val startTime = getTime(startTimeString)
        val endTime = getTime(endTimeString)
        binding.btSlot.text = "$startTime-$endTime"
        binding.btSlot.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    private fun getTime(timeString: String): String {
        return AppDateFormats.DF_TIME_HH.format(timeString)
    }
}

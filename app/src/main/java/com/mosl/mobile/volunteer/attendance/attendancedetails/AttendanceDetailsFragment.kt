package com.mosl.mobile.volunteer.attendance.attendancedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
import com.mosl.mobile.volunteer.attendance.corporateqr.QrCodeScanContract
import com.mosl.mobile.volunteer.constants.NavigationConstants
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.AlertDialogBinding
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceDetailsBinding
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.EMPTY_STRING
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

    private val companyId by lazy {
        requireArguments().getString(QrCodeScanContract.EXTRA_COMPANY_ID).orEmpty()
    }

    private val corporateName by lazy {
        requireArguments().getString(QrCodeScanContract.EXTRA_CORPORATE_NAME).orEmpty()
    }
    override fun getLayout(): Int {
        return R.layout.fragment_attendance_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceDetailsBinding.bind(view)
        remoteConfigModel =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        setUpViews()
        initData()


        binding.btYes.clicks()
            .subscribe { subject.onNext(AttendanceDetailsEvent.YesNoClicked(isAllowed = 1)) }
            .addTo(disposable)

        binding.btNo.clicks()
            .subscribe { subject.onNext(AttendanceDetailsEvent.YesNoClicked(isAllowed = 0)) }
            .addTo(disposable)

        presenter.start()
    }

    private fun initData() {
//        val banner: RemoteConfigModel? =
//            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
       /* Glide.with(this)
            .load(remoteConfigModel?.headerImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.header.icMosl)*/
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
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
            userManager.clearClientModel()
            screenNavigator.openHome()
        }
    }

    override fun onBackPressed(): Boolean {
        screenNavigator.openPage(
            pageId = NavigationConstants.NAVIGATE_TO_QR_CODE_SCAN_FRAGMENT.toString(),
            args = Bundle().apply {
                putString(QrCodeScanContract.EXTRA_COMPANY_ID, companyId)
                putString(QrCodeScanContract.EXTRA_CORPORATE_NAME, corporateName)
            },
            addToStack = false
        )
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    private fun setUpViews() {
        (requireActivity() as? MOSLCommonActivity)?.toggleAppBarVisibility(isVisible = false)
        val clientModel = userManager.getClientModel()
        if (clientModel?.message.isNullOrEmpty().not()){
            alertMessage(clientModel?.message?:"No data")
        }else {
            binding.apply {
                tvCorporateName.text = corporateName
                btFundName.text = clientModel?.fundName.orEmpty()
                btClientName.text = clientModel?.clientName.orEmpty()
                btStatus.text = clientModel?.status.orEmpty()
                if (btStatus.text == getString(R.string.bt_status_registered_label)) {
                    btStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color._green))
                } else {
                    btStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                }
            }
            setTimeSlot(clientModel?.startTime, clientModel?.endTime, clientModel?.status)
        }
    }
    private lateinit var bindingDialog: AlertDialogBinding
    private fun alertMessage(message: String) {

        //Inflate the dialog with custom view
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.alert_dialog, null)

        bindingDialog = AlertDialogBinding.bind(dialogView)


        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
        val mAlertDialog = mBuilder.show()
        bindingDialog.txtError.text = message

        //cancel button click of custom layout
        bindingDialog.btnOk.setOnClickListener {
            //dismiss dialog
            onBackPressed()
            mAlertDialog.dismiss()
        }
    }

    private fun setTimeSlot(startTimeString: String?, endTimeString: String?, status: String?) {
        if (startTimeString.isNullOrEmpty() ||
            endTimeString.isNullOrEmpty() ||
            status.isNullOrEmpty() ||
            status == getString(R.string.bt_status_unregistered_label)
        ) {
            binding.btSlot.text = getString(R.string.bt_time_slot_na_label)
            binding.btSlot.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            return
        }

        val startTime = getTime(startTimeString)
        val endTime = getTime(endTimeString)
        binding.btSlot.text = "$startTime-$endTime"
        binding.btSlot.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_text_color))
    }

    private fun getTime(timeString: String): String {
        val date = AppDateFormats.DF_JSON_DATE_4.parse(timeString) ?: return EMPTY_STRING
        return AppDateFormats.DF_TIME_HH.format(date)
    }
}

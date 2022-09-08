package com.mosl.mobile.volunteer.attendance.corporateqr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.commons.date.CalendarUtils
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.attendance.corporateqr.QrCodeScanContract.Companion.EXTRA_COMPANY_ID
import com.mosl.mobile.volunteer.constants.NavigationConstants
import com.mosl.mobile.volunteer.databinding.FragmentQrCodeScanBinding
import com.mosl.mobile.volunteer.appuser.login.UserManagerImpl
import com.mosl.mobile.volunteer.attendance.attendancedetails.mapToClientDetails
import com.mosl.mobile.volunteer.domain.attendance.ClientDetailsModel
import com.mosl.shared.AppDateFormats
import com.mosl.shared.CheckPermissionHelper
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.rxkotlin.ofType
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber
import javax.inject.Inject

class QrCodeScanFragment: MOSLCommonFragment(), ZXingScannerView.ResultHandler, QrCodeScanContract {

    private lateinit var binding: FragmentQrCodeScanBinding

    @Inject
    lateinit var presenter: QrCodeScanPresenter

    @Inject
    lateinit var userManagerImpl: UserManagerImpl

    private val companyId by lazy {
        requireArguments().getString(EXTRA_COMPANY_ID).orEmpty()
    }

    private val subject = PublishSubject.create<QrCodeScanEvent>()

    private val checkPermissionHelper by lazy {
        CheckPermissionHelper(requireActivity() as AppCompatActivity, this)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_qr_code_scan
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQrCodeScanBinding.bind(view)
        binding.qrCodeView.setResultHandler(this)
        checkPermissionHelper.checkAndRequestPermissions()
        presenter.start()
    }

    override fun handleResult(rawResult: Result?) {
        Timber.d("Qr Code Value: ${rawResult?.text}")
        val result = rawResult?.text
        result?.let {
            binding.qrCodeView.stopCamera()
            subject.onNext(QrCodeScanEvent.SuccessEvent(
                uniqueId = it,
                companyId = companyId,
                confDate = "2022-06-09"
            ))
        }
    }

    override fun onQrCodeScanSuccess(): Observable<QrCodeScanEvent.SuccessEvent> {
        return subject.ofType()
    }

    override fun renderQrCodeScanSuccess(uiModel: UiModel<ClientDetailsModel>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            showOnFailurePopup(it)
        }
        uiModel.onSuccess {
            userManagerImpl.saveClientModel(it.mapToClientDetails())

            screenNavigator.openPage(
                NavigationConstants.NAVIGATE_TO_ATTENDANCE_DETAILS_FRAGMENT,
                true
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        checkPermissionHelper.onPermissionsResult(
            requestCode = requestCode,
            grantResults = grantResults
        )
    }

    override fun onResume() {
        super.onResume()
        binding.qrCodeView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.qrCodeView.stopCamera()
    }

    override fun isBackPressEnabled(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.qrCodeView.stopCamera()
        presenter.stop()
    }
}
package com.mosl.mobile.volunteer.corporateqr

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.FragmentQrCodeScanBinding
import com.mosl.shared.CheckPermissionHelper
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber

class QrCodeScanFragment: MOSLCommonFragment(), ZXingScannerView.ResultHandler, QrCodeScanContract {

    private lateinit var binding: FragmentQrCodeScanBinding
    private lateinit var scannerView: ZXingScannerView

    private val checkPermissionHelper by lazy {
        CheckPermissionHelper(requireActivity() as AppCompatActivity, this)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_qr_code_scan
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQrCodeScanBinding.bind(view)
        scannerView = ZXingScannerView(requireContext())
        binding.qrCodeView.setResultHandler(this)
        checkPermissionHelper.checkAndRequestPermissions()
    }

    override fun handleResult(rawResult: Result?) {
        Timber.d("Qr Code Value: ${rawResult?.text}")
        val result = rawResult?.text
        result?.let {
            binding.qrCodeView.stopCamera()
            // Go to Next Screen
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

    override fun onDestroy() {
        super.onDestroy()
        binding.qrCodeView.stopCamera()
    }
}
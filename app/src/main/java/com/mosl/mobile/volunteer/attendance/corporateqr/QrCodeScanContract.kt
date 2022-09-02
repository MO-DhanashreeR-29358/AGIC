package com.mosl.mobile.volunteer.attendance.corporateqr

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.attendance.ClientDetailsModel
import io.reactivex.Observable

interface QrCodeScanContract: BaseView {

    fun onQrCodeScanSuccess(): Observable<QrCodeScanEvent.SuccessEvent>
    fun renderQrCodeScanSuccess(uiModel: UiModel<ClientDetailsModel>)

    companion object {
       internal const val EXTRA_COMPANY_ID = "company_id"
    }
}
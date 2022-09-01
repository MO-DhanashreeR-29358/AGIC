package com.mosl.mobile.volunteer.attendance.corporateqr

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.data.api.attendance.ClientMeetingDetailsModel
import io.reactivex.Observable

interface QrCodeScanContract: BaseView {

    fun onQrCodeScanSuccess(): Observable<QrCodeScanEvent.SuccessEvent>
    fun renderQrCodeScanSuccess(uiModel: UiModel<ClientMeetingDetailsModel>)

    companion object {
       internal const val EXTRA_COMPANY_ID = "company_id"
    }
}
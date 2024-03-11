package com.mosl.mobile.volunteer.attendance.corporateqr

sealed class QrCodeScanEvent {
    data class SuccessEvent(
        val uniqueId: String,
        val companyId: String,
        val confDate: String
        ): QrCodeScanEvent()
}

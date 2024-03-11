package com.mosl.mobile.volunteer.attendance.corporateqr

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import javax.inject.Inject

class QrCodeScanPresenter @Inject constructor(
    val repository: AttendanceRepository,
    view: QrCodeScanContract
): BasePresenter<QrCodeScanContract>(view) {
    override fun start() {
        view.onQrCodeScanSuccess()
            .switchMap {
                repository.getClientMeeting(
                        it.uniqueId,
                        it.companyId,
                        it.confDate
                    ).compose(applyUiModel())
            }.subscribe(view::renderQrCodeScanSuccess)
            .addTo(disposable)
    }
}
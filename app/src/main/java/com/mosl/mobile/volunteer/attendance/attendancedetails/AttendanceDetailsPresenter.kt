package com.mosl.mobile.volunteer.attendance.attendancedetails

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.volunteer.appuser.login.UserManagerImpl
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import javax.inject.Inject

class AttendanceDetailsPresenter @Inject constructor(
    private val userManagerImpl: UserManagerImpl,
    private val repository: AttendanceRepository,
    view: AttendanceDetailsContract
): BasePresenter<AttendanceDetailsContract>(view) {
    override fun start() {
        view.onYesNoBtnClicked()
            .switchMap {
                val clientModel = userManagerImpl.getClientModel()
                repository.submitClientAttendance(
                    userId = clientModel?.userId.orEmpty(),
                    clientId = clientModel?.clientId ?: Int.MIN_VALUE,
                    fundId = clientModel?.fundId ?: Int.MIN_VALUE,
                    meetingId = clientModel?.meetingId ?: Int.MIN_VALUE,
                    companyId = clientModel?.companyId.orEmpty(),
                    status = clientModel?.status.orEmpty(),
                    startTime = clientModel?.startTime.orEmpty(),
                    endTime = clientModel?.endTime.orEmpty(),
                    isAllowed = it.isAllowed
                ).compose(applyUiModel())
            }.subscribe(view::renderAttendanceMarked)
            .addTo(disposable)
    }
}
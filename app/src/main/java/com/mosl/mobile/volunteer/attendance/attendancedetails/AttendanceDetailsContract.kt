package com.mosl.mobile.volunteer.attendance.attendancedetails

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface AttendanceDetailsContract: BaseView {
    fun onYesNoBtnClicked(): Observable<AttendanceDetailsEvent.YesNoClicked>
    fun renderAttendanceMarked(uiModel: UiModel<String>)
}
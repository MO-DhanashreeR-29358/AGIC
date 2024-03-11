package com.mosl.mobile.volunteer.attendance.attendancedetails

sealed class AttendanceDetailsEvent {
    data class YesNoClicked(val isAllowed: Int): AttendanceDetailsEvent()
}

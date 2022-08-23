package com.mosl.mobile.volunteer.contactrm

sealed class CallUiEvent {
  data class CallClick(@JvmField val mobileNo: String?) : CallUiEvent()
}
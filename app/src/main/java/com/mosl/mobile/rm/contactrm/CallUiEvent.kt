package com.mosl.mobile.rm.contactrm

sealed class CallUiEvent {
  data class CallClick(@JvmField val mobileNo: String?) : CallUiEvent()
}
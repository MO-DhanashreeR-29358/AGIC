package com.mosl.mobile.contactrm

sealed class CallUiEvent {
  data class CallClick(@JvmField val mobileNo: String?) : CallUiEvent()
}
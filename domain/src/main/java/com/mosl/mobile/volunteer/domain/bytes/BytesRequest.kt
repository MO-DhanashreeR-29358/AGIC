package com.mosl.mobile.volunteer.domain.bytes

data class BytesRequest (
    @JvmField  val bytesTimeStamp:String,
    @JvmField  val scheduleTimeStamp:String,
    @JvmField  val clientId:String
)
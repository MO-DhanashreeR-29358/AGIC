package com.mosl.mobile.domain.bytes

data class BytesRequest (
    @JvmField  val bytesTimeStamp:String,
    @JvmField  val scheduleTimeStamp:String,
    @JvmField  val clientId:String
)
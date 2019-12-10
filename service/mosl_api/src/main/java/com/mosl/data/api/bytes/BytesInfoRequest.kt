package com.mosl.data.api.bytes

import com.google.gson.annotations.SerializedName

data class BytesInfoRequest(
    @JvmField @SerializedName ("ByteTimeStamp") val bytesTimeStamp:String,
    @JvmField @SerializedName ("ScheduleTimeStamp") val scheduleTimeStamp:String,
    @JvmField @SerializedName ("ClientId") val clientId:String
)
package com.mosl.data.api.bytes

import com.google.gson.annotations.SerializedName

data class BytesInfoResponse (

    @JvmField @SerializedName("SerialNumber") var serialNumber:String,
    @JvmField @SerializedName("BYTE") var byte:String,
    @JvmField @SerializedName("Entrydate") var entryDate:String

)
package com.mosl.mobile.data

import com.google.gson.annotations.SerializedName

data class RemoteConfigModel(

    @JvmField @SerializedName("splash") var splash:String,
    @JvmField @SerializedName("logo")var logo:String,
    @JvmField @SerializedName("year")var year:String,
    @JvmField @SerializedName("android")var android:AndroidModel
)

data class AndroidModel(

    @JvmField @SerializedName("version")var version:String,
    @JvmField @SerializedName("releaseNote")var releaseNote:String,
    @JvmField @SerializedName("isMandatory")var isMandatory:Boolean,
    @JvmField @SerializedName("appLink")var appLink:String
)
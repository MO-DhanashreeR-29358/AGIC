package com.mosl.mobile.data

data class RemoteConfigModel(

    @JvmField var splash:String,
    @JvmField var logo:String,
    @JvmField var year:String,
    @JvmField var android:AndroidModel
)

data class AndroidModel(

    @JvmField var version:String,
    @JvmField var releaseNote:String,
    @JvmField var isMandatory:Boolean,
    @JvmField var appLink:String
)
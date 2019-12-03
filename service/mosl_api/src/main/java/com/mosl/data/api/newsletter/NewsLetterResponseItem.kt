package com.mosl.data.api.newsletter

import com.google.gson.annotations.SerializedName

data class NewsLetterResponseItem(

    @JvmField @SerializedName("IMAGENAME")
    val imageName: String,

    @JvmField @SerializedName("IMAGEPATH")
    val imagePath: String,

    @JvmField @SerializedName("Title")
    val title: String,

    @JvmField @SerializedName("HeadLines")
    val headLines: String
)
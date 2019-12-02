package com.mosl.data.api.newsletter

import com.google.gson.annotations.SerializedName

data class NewsLetterResponse(

    @JvmField @SerializedName("Data")
    var newsLetterResponseItem: List<NewsLetterResponseItem>? = null,

    @JvmField @SerializedName("IsActive")
    val IsActive: String

)


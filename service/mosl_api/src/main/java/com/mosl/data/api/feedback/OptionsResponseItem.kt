package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class OptionsResponseItem(
    @JvmField @SerializedName("optionId")
    val optionId: String,

    @JvmField @SerializedName("optionId")
    val optionName: String,

    @JvmField @SerializedName("optionId")
    val selected: Boolean
)
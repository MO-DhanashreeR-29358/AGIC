package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class OptionsResponseItem(
    @JvmField @SerializedName("optionId")
    val optionId: String,

    @JvmField @SerializedName("optionName")
    val optionName: String,

    @JvmField @SerializedName("selected")
    val selected: Boolean
)
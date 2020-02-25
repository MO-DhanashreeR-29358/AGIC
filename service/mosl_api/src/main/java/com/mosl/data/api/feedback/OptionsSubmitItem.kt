package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class OptionsSubmitItem(
    @JvmField @SerializedName("optionId")
    val optionId: String,

    @JvmField @SerializedName("selected")
    val selected: Boolean
)
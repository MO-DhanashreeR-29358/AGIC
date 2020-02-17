package com.mosl.mobile.domain.feedback

data class OptionsResponse(
    @JvmField val optionId: String,

    @JvmField val optionName: String,

    @JvmField val selected: Boolean
)
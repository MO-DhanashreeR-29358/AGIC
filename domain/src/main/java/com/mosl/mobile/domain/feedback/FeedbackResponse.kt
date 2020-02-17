package com.mosl.mobile.domain.feedback

data class FeedbackResponse(
    @JvmField val questionId: String,

    @JvmField val questionTitle: String,

    @JvmField val questionType: String,

    @JvmField val options: List<OptionsResponse>,

    @JvmField val defaultValue: String
)
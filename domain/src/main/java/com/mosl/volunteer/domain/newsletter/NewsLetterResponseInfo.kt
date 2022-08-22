package com.mosl.volunteer.domain.newsletter

data class NewsLetterResponseInfo(

    @JvmField var Data: List<NewsLetterResponseItemInfo>,
    @JvmField val IsActive: Boolean
)
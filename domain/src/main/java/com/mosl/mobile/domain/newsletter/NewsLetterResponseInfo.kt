package com.mosl.mobile.domain.newsletter

data class NewsLetterResponseInfo(

    @JvmField var Data: List<NewsLetterResponseItemInfo>? = null,
    @JvmField val IsActive: Boolean
)
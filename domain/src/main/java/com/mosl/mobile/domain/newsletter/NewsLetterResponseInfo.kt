package com.mosl.mobile.domain.newsletter

data class NewsLetterResponseInfo(

    @JvmField var Data: List<NewsLetterResponseItemInfo>,
    @JvmField val IsActive: Boolean
)
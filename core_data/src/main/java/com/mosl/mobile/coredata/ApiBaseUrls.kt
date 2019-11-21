package com.mosl.mobile.coredata

interface ApiBaseUrls : CommonBaseUrls {
}

interface CommonBaseUrls {
    fun getMoslApiUrl(): String
}
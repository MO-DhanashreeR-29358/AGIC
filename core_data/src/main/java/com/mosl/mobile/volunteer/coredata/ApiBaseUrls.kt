package com.mosl.mobile.volunteer.coredata

interface ApiBaseUrls : CommonBaseUrls {
}

interface CommonBaseUrls {
    fun getMoslApiUrl(): String
}
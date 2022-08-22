package com.mosl.volunteer.coredata

interface ApiBaseUrls : CommonBaseUrls {
}

interface CommonBaseUrls {
    fun getMoslApiUrl(): String
}
package com.mosl.mobile

import com.mosl.mobile.data.ApiBaseUrls

class ApiBaseUrlImpl:ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://uatapi.motilaloswal.com/InternalAPIv4/api/"
  }
}
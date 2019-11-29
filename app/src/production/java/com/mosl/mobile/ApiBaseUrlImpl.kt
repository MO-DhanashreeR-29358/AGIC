package com.mosl.mobile

import com.mosl.mobile.data.ApiBaseUrls

class ApiBaseUrlImpl:ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://agic.motilaloswal.com/AGIC_MobileAPI/api/"
  }
}
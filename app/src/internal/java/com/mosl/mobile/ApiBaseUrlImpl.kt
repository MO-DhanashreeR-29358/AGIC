package com.mosl.mobile

import com.mosl.mobile.coredata.ApiBaseUrls

class ApiBaseUrlImpl: ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://agic.motilaloswal.com/AGIC_MobileAPI/api/"
  }
}
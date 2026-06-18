package com.mosl.mobile.rm

import com.mosl.mobile.rm.coredata.ApiBaseUrls

class ApiBaseUrlImpl: ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://agic.motilaloswal.com/AGIC_MobileAPI/api/"
  }
}
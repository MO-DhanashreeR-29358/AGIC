package com.mosl.mobile

import com.mosl.mobile.coredata.ApiBaseUrls

class ApiBaseUrlImpl: ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://agic.motilaloswal.com/ConfMobileAPI_UAT/api/"
  }
}
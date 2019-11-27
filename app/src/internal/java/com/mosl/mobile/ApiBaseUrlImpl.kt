package com.mosl.mobile

import com.mosl.mobile.coredata.ApiBaseUrls

class ApiBaseUrlImpl: ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "http://wsn.motilaloswal.com:12345/"
  }
}
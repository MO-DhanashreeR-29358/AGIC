package com.mosl.volunteer

import com.mosl.volunteer.coredata.ApiBaseUrls

class ApiBaseUrlImpl: ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://agic.motilaloswal.com/AGIC_MobileAPI/api/"
  }
}
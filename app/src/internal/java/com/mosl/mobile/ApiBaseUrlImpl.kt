package com.mosl.mobile

import com.mosl.mobile.data.ApiBaseUrls

class ApiBaseUrlImpl:ApiBaseUrls {
  override fun getMoslApiUrl(): String {
    return "https://api.simplifiedcoding.in/course-apis/mvvm/"
  }
}
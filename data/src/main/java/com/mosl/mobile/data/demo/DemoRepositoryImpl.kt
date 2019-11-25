package com.mosl.mobile.data.demo

import com.mosl.data.api.MOSLApi
import com.mosl.mobile.data.network.SafeApiRequest
import com.mosl.mobile.demo.AuthResponse
import com.mosl.mobile.demo.AuthResponseM
import com.mosl.mobile.domain.demo.DemoRepository
import javax.inject.Inject

class DemoRepositoryImpl @Inject constructor(
  private val api: MOSLApi
) : DemoRepository, SafeApiRequest() {
  suspend override fun userLogin(email: String, password: String): AuthResponseM {
    return  apiRequest {  api.userLogin(email, password)}.run {
           this.transform()
    }
  }
}

fun AuthResponse.transform(): AuthResponseM {
  return AuthResponseM(
    isSuccessful=isSuccessful,
    message = message,
    user = user

  )
}



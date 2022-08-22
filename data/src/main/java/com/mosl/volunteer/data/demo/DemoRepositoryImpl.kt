package com.mosl.volunteer.data.demo

import com.mosl.data.api.AGICApi
import com.mosl.volunteer.demo.UserResponse
import com.mosl.volunteer.demo.UserResponseMapper
import com.mosl.volunteer.domain.demo.DemoRepository
import com.mosl.data.api.demo.LoginInfo
import com.mosl.volunteer.domain.demo.LoginInfoD
import javax.inject.Inject

class DemoRepositoryImpl @Inject constructor(
  private val api: AGICApi
) : DemoRepository {
  /*suspend override fun userLogin(loginInfo: LoginInfo): UserResponseMapper {
    return api.userLogin(authType,email, password).run {
      this.transform()
    }

  }*/

  override suspend fun userLogin(loginDomain: LoginInfoD): UserResponseMapper {
   val request =  loginDomain.mapToLoginInfo()
   return api.demoLogin(request).run {
     this.transform()
   }

 }

}

fun UserResponse.transform(): UserResponseMapper {
  return UserResponseMapper(
    id=id,
    name = name,
    email = email,
    password = password,
    email_verified_at = email_verified_at,
    created_at = created_at,
    updated_at =updated_at

  )
}

fun LoginInfoD.mapToLoginInfo(): LoginInfo {
  return LoginInfo(
    authType, password, employeeId, ipAddress
  )
}



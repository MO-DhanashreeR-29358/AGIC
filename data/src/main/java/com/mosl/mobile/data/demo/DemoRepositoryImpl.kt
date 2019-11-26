package com.mosl.mobile.data.demo

import com.mosl.data.api.MOSLApi
import com.mosl.mobile.demo.UserResponse
import com.mosl.mobile.demo.UserResponseMapper
import com.mosl.mobile.domain.demo.DemoRepository
import javax.inject.Inject

class DemoRepositoryImpl @Inject constructor(
  private val api: MOSLApi
) : DemoRepository {
  suspend override fun userLogin(email: String, password: String): UserResponseMapper {
    return api.userLogin(email, password).run {
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



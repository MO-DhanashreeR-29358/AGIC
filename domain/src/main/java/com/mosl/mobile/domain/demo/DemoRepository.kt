package com.mosl.mobile.domain.demo

import com.mosl.mobile.demo.UserResponseMapper

interface DemoRepository {
  suspend fun userLogin(email: String, password: String): UserResponseMapper
}
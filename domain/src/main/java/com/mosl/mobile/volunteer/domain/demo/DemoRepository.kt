package com.mosl.mobile.volunteer.domain.demo

import com.mosl.mobile.volunteer.demo.UserResponseMapper

interface DemoRepository {
  suspend fun userLogin(loginInfo: LoginInfoD): UserResponseMapper
}
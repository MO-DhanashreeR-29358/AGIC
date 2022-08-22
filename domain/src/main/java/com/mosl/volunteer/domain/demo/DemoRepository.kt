package com.mosl.volunteer.domain.demo

import com.mosl.volunteer.demo.UserResponseMapper

interface DemoRepository {
  suspend fun userLogin(loginInfo: LoginInfoD): UserResponseMapper
}
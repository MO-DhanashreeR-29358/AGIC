package com.mosl.mobile.domain.demo

import com.mosl.mobile.demo.AuthResponseM

interface DemoRepository {
  suspend fun userLogin(email: String, password: String): AuthResponseM
}
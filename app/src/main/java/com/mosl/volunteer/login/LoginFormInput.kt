package com.mosl.volunteer.login

import com.mosl.volunteer.R
import com.mosl.volunteer.appuser.model.LoginRequest
import com.mosl.validator.core.Validator
import com.mosl.validator.data.ValidationResult

data class LoginFormInput(
  val uniqueId: String,
  val deviceId: String
)

object LoginValidator : Validator<LoginFormInput, LoginRequest>() {
  override fun execute(input: LoginFormInput): ValidationResult<LoginRequest> {
    val errorMessage: Int = when {
      input.uniqueId.isEmpty() -> R.string.msg_unique_code_empty
      else -> -1
    }

    return if (errorMessage != -1) {
      withError(errorMessage)
    } else {
      withData(
          LoginRequest(
              uniqueId = input.uniqueId,
              deviceId = input.deviceId
          )
      )
    }
  }
}
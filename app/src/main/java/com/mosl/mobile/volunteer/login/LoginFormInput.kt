package com.mosl.mobile.volunteer.login

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.appuser.model.LoginRequest
import com.mosl.validator.core.Validator
import com.mosl.validator.data.ValidationResult

data class LoginFormInput(
    val empId: String,
    val confDate: String,
    val password: String
)

object LoginValidator : Validator<LoginFormInput, LoginRequest>() {
    override fun execute(input: LoginFormInput): ValidationResult<LoginRequest> {
        val errorMessage: Int = when {
            input.empId.isEmpty() -> R.string.msg_unique_code_empty
            input.password.isEmpty() -> R.string.msg_password_empty
            else -> -1
        }

        return if (errorMessage != -1) {
            withError(errorMessage)
        } else {
            withData(
                LoginRequest(
                    empId = input.empId,
                    confDate = "2022-06-09", // Change
                    password = input.password
                )
            )
        }
    }
}
package com.mosl.validator.core

import androidx.annotation.StringRes
import com.mosl.validator.data.ValidationResult
import com.mosl.validator.data.ValidationResult.Failure
import com.mosl.validator.data.ValidationResult.Success
import com.mosl.validator.exception.ValidationException
import io.reactivex.Observable

abstract class Validator<in T, R> {
  protected abstract fun execute(input: T): ValidationResult<R>

  fun validate(input: T): Observable<R> {
    return Observable.fromCallable {
      validateNow(input)
    }
  }

  @Throws(ValidationException::class)
  fun validateNow(input: T): R {
    return when (val output = execute(input)) {
      is Success -> output.data
      is Failure -> throw ValidationException(output.error)
    }
  }

  fun run(input: T): ValidationResult<R> {
    return execute(input)
  }

  protected fun withData(data: R): ValidationResult<R> {
    return success(data)
  }

  protected fun success(data: R): ValidationResult<R> {
    return ValidationResult.success(data)
  }

  @JvmOverloads
  protected fun withError(@StringRes error: Int, identifier: Any? = null): ValidationResult<R> {
    return failure(error, identifier)
  }

  @JvmOverloads
  protected fun failure(@StringRes error: Int, identifier: Any? = null): ValidationResult<R> {
    return ValidationResult.failure(error, identifier)
  }

  @JvmOverloads
  protected fun withMessage(message: String, identifier: Any? = null): ValidationResult<R> {
    return failure(message, identifier)
  }

  @JvmOverloads
  protected fun failure(message: String, identifier: Any? = null): ValidationResult<R> {
    return ValidationResult.failure(message, identifier)
  }
}
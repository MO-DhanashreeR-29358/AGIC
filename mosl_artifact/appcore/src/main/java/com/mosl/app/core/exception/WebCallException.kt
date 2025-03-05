package com.mosl.app.core.exception

/**
 * Created by lalkishore on 1/31/2017.
 */

open class WebCallException(val errorMessage: String) : Exception() {
  override fun toString(): String {
    return "WebCallException(errorMessage='$errorMessage')"
  }
}
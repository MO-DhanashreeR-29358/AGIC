package com.mosl.mobile.coredata

import androidx.annotation.WorkerThread
import io.reactivex.Single

interface ErrorRepository {
  fun getMessage(code: String): Single<String>
  @WorkerThread
  fun getMessageBlocking(code: String): String

  fun getInteractiveErrorMessage(
      errorCode: Int,
      exchangeCode: Int = -1
  ): Single<String>
}
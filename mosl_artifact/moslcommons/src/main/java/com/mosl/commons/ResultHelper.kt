@file:JvmName("ResultHelper")

package com.mosl.commons

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

fun <T> applyResult(): ObservableTransformer<T, Result<T>> {
  return ObservableTransformer { observable ->
    observable.withResult()
  }
}

fun <T> Observable<T>.withResult(): Observable<Result<T>> {
  return map { Result.data(it) }
      .onErrorReturn { Result.empty(it) }
}

inline fun <T> Result<T>.onData(action: (data: T) -> Unit) {
  if (data != null) {
    action(data)
  }
}

inline fun Result<*>.onError(action: (error: Throwable) -> Unit) {
  if (error != null) {
    action(error)
  }
}
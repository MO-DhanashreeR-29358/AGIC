package com.mosl.commons.data

import com.mosl.commons.data.UiModel.Fail
import com.mosl.commons.data.UiModel.Success
import com.mosl.commons.rx.Dispatchers
import com.mosl.commons.rx.ofType
import io.reactivex.Observable
import io.reactivex.ObservableTransformer


fun <T : Any> Dispatchers.applyUiModel(): ObservableTransformer<T, UiModel<T>> {
    return ObservableTransformer { observable ->
        observable.map { UiModel.success(it) }
            .onErrorReturn { UiModel.error(it) }
            .observeOn(main)
            .startWith(UiModel.InProgress)
    }
}

fun <T : Any> Dispatchers.applyRvUiModel(): ObservableTransformer<T, UiModel<T>> {
    return ObservableTransformer { observable ->
        observable.map { UiModel.success(it) }
            .onErrorReturn { UiModel.error(it) }
            .startWith(UiModel.InProgress)
            .observeOn(main)
    }
}

inline fun <T> UiModel<T>.onSuccess(action: (data: T) -> Unit) {
    if (this is Success) {
        action(this.value)
    }
}

inline fun UiModel<*>.onFailure(action: (error: Throwable) -> Unit) {
    if (this is Fail) {
        action(this.throwable)
    }
}

/**
 * Filter all type of Error generated UpStream
 */
//fun <T> Observable<T>.skipErrors(): Observable<T & Any> {
//    return map { UiModel.success(it) }
//        .onErrorReturn { UiModel.error(it) }
//        .filterSuccess()
//}


/**
 * Filter only succeeded UiModel events unwrapped to T type.
 */
fun <T> Observable<UiModel<T>>.filterSuccess(): Observable<T> {
    return ofType<UiModel.Success<T>>()
        .map { it.value }
}

/**
 * Filter only failed UiModel events unwrapped to Throwable type
 */
fun <T> Observable<UiModel<T>>.filterFailure(): Observable<Throwable> {
    return ofType<UiModel.Fail<T>>()
        .map { it.throwable }
}
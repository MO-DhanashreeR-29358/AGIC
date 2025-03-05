@file:JvmName("RxDispatchers")
package com.mosl.app.core.rx

import com.mosl.commons.data.UiModel
import com.mosl.commons.data.applyRvUiModel
import com.mosl.commons.data.applyUiModel
import com.mosl.commons.rx.Dispatchers
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

@JvmField
val DefaultDispatchers = Dispatchers(
    io = Schedulers.io(),
    computation = Schedulers.computation(),
    main = AndroidSchedulers.mainThread(),
    db = Schedulers.from(Executors.newSingleThreadExecutor {
      Thread(it, "db").apply { isDaemon = true }
    })
)

fun <T : Any> applyUiModel(): ObservableTransformer<T, UiModel<T>> {
  return DefaultDispatchers.applyUiModel()
}

fun <T : Any> applyRvUiModel(): ObservableTransformer<T, UiModel<T>> {
  return DefaultDispatchers.applyRvUiModel()
}
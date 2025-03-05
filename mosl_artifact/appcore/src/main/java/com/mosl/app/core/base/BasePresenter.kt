package com.mosl.app.core.base

import androidx.annotation.CallSuper
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.rx.DefaultDispatchers
import com.mosl.commons.data.applyRvUiModel
import com.mosl.commons.data.applyUiModel
import com.mosl.commons.rx.Dispatchers
import com.mosl.commons.rx.ofType
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit.MILLISECONDS

/**
 * Created by krutikapatil on 08-01-2018.
 */
abstract class BasePresenter<out T : BaseView> @JvmOverloads constructor(
    @JvmField protected val view: T,
    @JvmField protected val dispatchers: Dispatchers = DefaultDispatchers
) {
  @JvmField
  protected val disposable: CompositeDisposable = CompositeDisposable()

  fun onFetchCalled(): Observable<out DataAction.Fetch> {
    return view.onDataAction().ofType()
  }

  fun onRetryCalled(): Observable<out DataAction.Retry> {
    return view.onDataAction().ofType<DataAction.Retry>()
        .filter { view.isDataEmpty() }
  }

  fun onRefreshCalled(): Observable<out DataAction.Refresh> {
    return view.onDataAction().ofType()
  }

  fun onFetchOrRetry(timeout: Long): Observable<DataAction> {
    return Observable.merge(onFetchCalled(), onRetryCalled())
        .debounce(timeout, MILLISECONDS, dispatchers.main)
  }

  fun onRetrySection(sectionId: Int): Observable<out DataAction.RetrySection> {
    return view.onDataAction().ofType<DataAction.RetrySection>()
        .filter { (id) -> id == sectionId }
  }

  fun <T : Any> applyUiModel() = dispatchers.applyUiModel<T>()

  fun <T : Any> applyRvUiModel() = dispatchers.applyRvUiModel<T>()

  abstract fun start()

  @CallSuper
  open fun stop() {
    disposable.clear()
  }
}
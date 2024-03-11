package com.mosl.mobile.volunteer.dashboard

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.base.BaseView
import com.mosl.commons.rx.CharSequenceToString
import dagger.Binds
import dagger.Module
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Module
abstract class DashboardModule {
  @Binds
  abstract fun provideDashboardView(fragment: DashboardFragment): DashboardContract.View
}

interface DashboardContract {
  interface View : BaseView {
    fun onSearchChanged(): Observable<CharSequence>
    fun renderPage(data: List<DashboardHeader>)
  }
}

class DashboardPresenter @Inject constructor(
  view: DashboardContract.View
) : BasePresenter<DashboardContract.View>(view) {
  private val repository: DashboardRepository = DashboardRepositoryImpl

  override fun start() {
    disposable.add(Observable.merge(onFetchCalled(), onRetryCalled())
        .switchMap { repository.getAll() }
        .subscribe(view::renderPage))

    disposable.add(
        view.onSearchChanged()
            .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .map(CharSequenceToString)
            .distinctUntilChanged()
            .switchMap { repository.search(it) }
            .subscribe(view::renderPage))
  }
}
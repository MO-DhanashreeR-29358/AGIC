package com.mosl.mobile.rm.newsletter

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.domain.newsletter.NewsLetterRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class NewsLetterPresenter @Inject constructor(
    view: NewsLetterView,
    val repository: NewsLetterRepository
) : BasePresenter<NewsLetterView>(view) {


    override fun start() {

        Observable.merge(onFetchCalled(), onRetryCalled())
            .switchMap {
                repository.brochureData()
                    .compose(applyUiModel())
            }
            .subscribe {
                view.renderBrochureData(it)
            }
            .addTo(disposable)

        view.onSkipClicked()
            .compose(applyUiModel())
            .subscribe {
                view.renderSkip(it)
            }
            .addTo(disposable)
    }

}
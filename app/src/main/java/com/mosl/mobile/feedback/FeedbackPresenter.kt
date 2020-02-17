package com.mosl.mobile.feedback

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.domain.feedback.FeedbackRepository
import javax.inject.Inject

class FeedbackPresenter @Inject constructor(
    view: FeedbackContract,
    val repository: FeedbackRepository
) : BasePresenter<FeedbackContract>(view) {

    override fun start() {
        onFetchCalled()
            .switchMap {
                repository.getFeedbackData()
            }
            .compose(applyUiModel())
            .subscribe {
                view.render(it)
            }.addTo(disposable)
    }
}
package com.mosl.mobile.volunteer.feedback

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.volunteer.domain.feedback.FeedbackRepository
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class FeedbackPresenter @Inject constructor(
    view: FeedbackContract,
    val repository: FeedbackRepository
) : BasePresenter<FeedbackContract>(view) {

    override fun start() {
        Observable.merge(onFetchCalled(), onRetryCalled())
            .switchMap {
                repository.getFeedbackData()
                    .compose(applyUiModel())
            }
            .subscribe {
                view.render(it)
            }.addTo(disposable)

        view.onSubmitButtonClicked()
            .map {
                view.getSurveyRequest()
            }
            .switchMap { surveyRequest ->
                repository.submitFeedback(surveyRequest)
                    .compose(applyUiModel())
            }
            .subscribe(view::renderSubmit, Timber::d)
            .addTo(disposable)


    }
}
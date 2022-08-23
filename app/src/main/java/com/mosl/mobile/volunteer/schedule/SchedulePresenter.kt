package com.mosl.mobile.volunteer.schedule

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.volunteer.domain.schedule.ScheduleRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    view: ScheduleContract,
    val repository: ScheduleRepository

) : BasePresenter<ScheduleContract>(view) {

    override fun start() {


        Observable.merge(onFetchCalled(), onRetryCalled()).switchMap {
            repository.getScheduleData()
                .compose(applyUiModel())
        }.subscribe {
                view.renderScheduleData(it)
            }.addTo(disposable)


        view.onItemClicked()
            .subscribe {
                view.renderItemData(it.Rep)
            }.addTo(disposable)

        view.onItemClickedMain()
            .subscribe {
                view.renderItemToCalender(it.objScheduleResponseItemInfo)
            }
            .addTo(disposable)
    }


}
package com.mosl.mobile.schedule

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.LastScheduleDate
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.shared.util.getTodaysDate
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
        }
            .compose(applyUiModel())
            .subscribe {
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
package com.mosl.mobile.schedule

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.shared.util.getTodaysDate
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    view: ScheduleContract,
    val repository: ScheduleRepository,
    val userManager: UserManager
) : BasePresenter<ScheduleContract>(view) {

    override fun start() {

        val scheduleRequest = ScheduleRequestInfo(
            clientID = userManager.user.clientId,
            schedule = getTodaysDate(),
//            schedule = "2019-08-20",
            timestamp = NEW
        )

        repository.getScheduleData(scheduleRequest)
            .compose(applyUiModel())
            .subscribe {
                view.renderScheduleData(it)
            }
            .addTo(disposable)

        view.onItemClicked()
            .subscribe {
                view.renderItemData(it.Rep)
            }
            .addTo(disposable)

        view.onItemClickedMain()
            .subscribe {
                view.renderItemToCalender(it.objScheduleResponseItemInfo)
            }
            .addTo(disposable)
    }

    companion object{
        const val NEW = "New"
    }
}
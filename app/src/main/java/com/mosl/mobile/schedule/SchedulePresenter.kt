package com.mosl.mobile.schedule

import android.util.Log
import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    view: ScheduleContract,
    val repository: ScheduleRepository,
    val userManager: UserManager
) : BasePresenter<ScheduleContract>(view) {

    override fun start() {

        val scheduleRequest = ScheduleRequestInfo(
            ClientID = userManager.user.clientId,
            schedule = "2019-08-20",
            timestamp = "New"
        )

//        repository.getScheduleData(view.getScheduleRequestInfo())
        repository.getScheduleData(scheduleRequest)
            .compose(applyUiModel())
            .subscribe {
                view.renderScheduleData(it)
            }
            .addTo(disposable)

        view.onItemClicked()
            .subscribe {
                Log.d("Test", it.Rep.toString())
                view.renderItemData(it.Rep)

            }
            .addTo(disposable)
    }

}
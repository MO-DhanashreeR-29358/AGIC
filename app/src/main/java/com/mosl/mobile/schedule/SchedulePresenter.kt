package com.mosl.mobile.schedule

import android.util.Log
import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SchedulePresenter @Inject constructor(
    view: ScheduleContract,
    val repository: ScheduleRepository
) : BasePresenter<ScheduleContract>(view) {

    override fun start() {

        Log.d("1", view.getScheduleRequestInfo().timestamp)
        Log.d("2", view.getScheduleRequestInfo().ClientID)
        Log.d("3", view.getScheduleRequestInfo().schedule)

        repository.getScheduleData(view.getScheduleRequestInfo())
            .compose(applyUiModel())
            .subscribe {
                view.renderScheduleData(it)
            }
            .addTo(disposable)
    }

}
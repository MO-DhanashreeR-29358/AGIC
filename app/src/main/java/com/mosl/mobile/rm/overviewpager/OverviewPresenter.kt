package com.mosl.mobile.rm.overviewpager

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.domain.overviewpager.OverviewRepository
import com.mosl.mobile.domain.overviewpager.RmOverviewRequestInfo
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
    view: OverviewPagerContract,
    val repository: OverviewRepository,
    val userManager: UserManager
) : BasePresenter<OverviewPagerContract>(view) {

    override fun start() {

        val objOverviewPagerRequestInfo = RmOverviewRequestInfo(
            ClientID = userManager.user.empCode,
        )
        Observable.merge(onFetchCalled(), onRetryCalled())
            .switchMap {
                repository.getRmOverviewData(objOverviewPagerRequestInfo)
                    .compose(applyUiModel())
            }.subscribe {
                view.renderScheduleData(it)
            }.addTo(disposable)

        view.onClientWiseClicked()
            .switchMap { repository.getRmClientOverviewData(objOverviewPagerRequestInfo)
                .compose(applyRvUiModel())}
            .subscribe { view.renderClientWiseData(it) }
            .addTo(disposable)

        view.onDayWiseClicked()
            .switchMap {
                repository.getRmOverviewData(objOverviewPagerRequestInfo)
                    .compose(applyUiModel())
            }.subscribe {
                view.renderScheduleData(it)
            }.addTo(disposable)
    }

    companion object {
        const val Over = "Over"
    }
}
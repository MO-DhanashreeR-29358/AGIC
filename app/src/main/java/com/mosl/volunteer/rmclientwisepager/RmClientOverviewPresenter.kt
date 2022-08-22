package com.mosl.volunteer.rmclientwisepager

import com.mosl.app.core.base.BasePresenter
import com.mosl.volunteer.appuser.login.UserManager
import com.mosl.volunteer.appuser.login.user
import com.mosl.volunteer.domain.overviewpager.OverviewRepository
import com.mosl.volunteer.domain.overviewpager.RmOverviewRequestInfo
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RmClientOverviewPresenter @Inject constructor(
    view: RmClientOverviewPagerContract,
    val repository: OverviewRepository,
    val userManager: UserManager
) : BasePresenter<RmClientOverviewPagerContract>(view) {

    override fun start() {

        val objOverviewPagerRequestInfo = RmOverviewRequestInfo(
            ClientID = userManager.user.empCode,
        )
        Observable.merge(onFetchCalled(), onRetryCalled())
            .switchMap { repository.getRmClientOverviewData(objOverviewPagerRequestInfo)
                .compose(applyRvUiModel())}
            .subscribe { view.renderClientWiseData(it) }.addTo(disposable)
    }

    companion object {
        const val Over = "Over"
    }
}
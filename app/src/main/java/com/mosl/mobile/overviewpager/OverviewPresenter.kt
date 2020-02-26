package com.mosl.mobile.overviewpager

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.overviewpager.OverviewRepository
import com.mosl.mobile.domain.overviewpager.OverviewRequestInfo
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
    view: OverviewPagerContract,
    val repository: OverviewRepository,
    val userManager: UserManager
) : BasePresenter<OverviewPagerContract>(view) {

    override fun start() {

        val objOverviewPagerRequestInfo = OverviewRequestInfo(
            ClientID = userManager.user.clientId,
            Over = Over
        )
        onFetchCalled().switchMap {
            repository.getOverviewData(objOverviewPagerRequestInfo)
                .compose(applyUiModel())
        }.subscribe {
                view.renderScheduleData(it)
            }.addTo(disposable)
    }

    companion object {
        const val Over = "Over"
    }
}
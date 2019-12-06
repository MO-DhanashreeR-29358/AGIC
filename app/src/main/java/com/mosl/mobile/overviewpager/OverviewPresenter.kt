package com.mosl.mobile.overviewpager

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.overviewpager.OverviewPagerRepository
import com.mosl.mobile.domain.overviewpager.OverviewPagerRequestInfo
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
    view: OverviewPagerContract,
    val repository: OverviewPagerRepository,
    val userManager: UserManager
) : BasePresenter<OverviewPagerContract>(view) {

    override fun start() {

        val objOverviewPagerRequestInfo = OverviewPagerRequestInfo(
            ClientID = userManager.user.clientId,
            Over = Over
        )

        repository.getOverviewData(objOverviewPagerRequestInfo)
            .compose(applyUiModel())
            .subscribe {
                view.renderScheduleData(it)
            }.addTo(disposable)
    }

    companion object {
        const val Over = "Over"
    }
}
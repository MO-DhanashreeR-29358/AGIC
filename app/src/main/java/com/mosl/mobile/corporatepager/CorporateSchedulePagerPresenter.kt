package com.mosl.mobile.corporatepager

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRepository
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRequestInfo
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CorporateSchedulePagerPresenter @Inject constructor(
    view: CorporateSchedulePagerContract,
    val repository: CorporateScheduleRepository,
    val userManager: UserManager
) : BasePresenter<CorporateSchedulePagerContract>(view) {

    override fun start() {

        val objCorporateScheduleRequestInfo = CorporateScheduleRequestInfo(
            ClientID = userManager.user.clientId,
            Over = Over
        )

        repository.getCorporateScheduleRepository(objCorporateScheduleRequestInfo)
            .compose(applyUiModel())
            .subscribe {
                view.renderPage(it)
            }.addTo(disposable)
    }

    companion object {
        const val Over = "Over"
    }
}
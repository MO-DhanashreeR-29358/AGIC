package com.mosl.mobile.corporatepager

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.LastNotificationDate
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRepository
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRequestInfo
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getMilliFromDate
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CorporateSchedulePagerPresenter @Inject constructor(
    view: CorporateSchedulePagerContract,
    val repository: CorporateScheduleRepository,
    @LastNotificationDate val lLastNotificationDate: StringPreference,
    val userManager: UserManager
) : BasePresenter<CorporateSchedulePagerContract>(view) {

    override fun start() {

        val notificationDateDate = if (lLastNotificationDate.get().isEmpty()) {
            NEW
        } else {
            getMilliFromDate(lLastNotificationDate.get(), AppDateFormats.DF_JSON_DATE_12)
        }

        val objCorporateScheduleRequestInfo = CorporateScheduleRequestInfo(
            ClientID = userManager.user.clientId,
            timestamp = notificationDateDate
        )
        onFetchCalled().switchMap {
            repository.getCorporateScheduleRepository(objCorporateScheduleRequestInfo)
                .compose(applyUiModel())
        }.subscribe {
            view.renderPage(it)
        }.addTo(disposable)

        view.onSearchItemClicked()
            .subscribe {
                view.renderSearchItemData(it.search)
            }.addTo(disposable)
    }

    companion object {
        const val NEW = "New"
    }
}
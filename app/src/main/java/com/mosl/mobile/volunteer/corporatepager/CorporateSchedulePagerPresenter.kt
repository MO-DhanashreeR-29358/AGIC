package com.mosl.mobile.volunteer.corporatepager

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.volunteer.annotations.LastNotificationDate
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.user
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleRepository
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleRequestInfo
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getMilliFromDate
import io.reactivex.Observable
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
            ZERO
        } else {
            getMilliFromDate(lLastNotificationDate.get(), AppDateFormats.DF_JSON_DATE_12).toString()
        }

        val objCorporateScheduleRequestInfo = CorporateScheduleRequestInfo(
            ClientID = userManager.user.empCode,
            timestamp = notificationDateDate
        )
        Observable.merge(onFetchCalled(), onRetryCalled()).switchMap {
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
        const val ZERO = "0"
    }
}
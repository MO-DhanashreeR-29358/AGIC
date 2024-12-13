package com.mosl.mobile.volunteer.coreui.core

import android.net.Uri
import android.os.Bundle
import androidx.annotation.CallSuper
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.base.BaseFragment
import com.mosl.mobile.volunteer.annotations.ClientCode
import com.mosl.mobile.volunteer.coreui.navigator.ScreenNavigator
import io.reactivex.Observable
import javax.inject.Inject

open class MOSLCommonFragment : BaseFragment() {
    @field:ClientCode
    lateinit var clientCode: StringPreference
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    protected fun onFetchCalled(): Observable<DataAction> {
        return dataActionSubject.filter { event -> event === DataAction.Fetch }
    }

    protected fun onRetryCalled(): Observable<DataAction> {
        return dataActionSubject.filter { event -> event === DataAction.Retry && isDataEmpty() }
    }

    protected fun onRefreshCalled(): Observable<DataAction> {
        return dataActionSubject.filter { event -> event === DataAction.Refresh }
    }

    /* fun isLoggedIn(): Boolean {
       return userManager.isLoggedIn()
     }*/

    /* fun isMFICustomer(): Boolean {
       return userManager.isMFICustomer()
     }*/

    /* fun isMFDCustomer(): Boolean {
       return userManager.isMFDCustomer()
     }*/

    fun pageNotAccessible() {
        screenNavigator.pageNotAccessible()
    }

    fun openWebUrl(url: Uri) {
//    requireContext().openWebUrl(url)
    }

    /*
    Navigation Handling
     */
    fun navigateTo(
        id: Int,
        bundle: Bundle,
        addStack: Boolean
    ) {
        val activity = activity
        if (activity != null) {
            hideKeyboard(activity.currentFocus)
            screenNavigator.openPage(id.toString(), bundle, addStack)
        }
    }

    fun goBackOnce() {
        screenNavigator.goBack()
    }

    fun goBackWithResult(args: Bundle) {
        screenNavigator.goBack(args)
    }

    @CallSuper
    override fun onFragmentResume() {
        super.onFragmentResume()
//    Crashlytics.setString("VisiblePage", this.toString())
    }
}
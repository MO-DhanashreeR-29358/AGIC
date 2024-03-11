package com.mosl.mobile.volunteer.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mosl.annotations.ActivityScoped
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.volunteer.annotations.ClientCode
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.user
import com.mosl.mobile.volunteer.constants.NavigationConstants
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.helper.CustomNavigator
import com.mosl.navigator.*
import javax.inject.Inject

@ActivityScoped
class ScreenNavigatorImpl @Inject constructor(
    activity: AppCompatActivity,
    userManager: UserManager,
    navigationHelper: NavigationHelper,
    @param:ClientCode
    protected val cCode: StringPreference,
    customNavigator: CustomNavigator,
    navigator: Navigator
) : ScreenNavigatorInternal(
    activity, userManager, navigationHelper, cCode,
    customNavigator, navigator
) {
    override fun goBack() {
        openPage(FragmentNavigationEntry.goBack())
    }

    override fun goBack(result: Bundle) {
        openPage(FragmentNavigationEntry.goBackWithResult(result))
    }

    override fun openHome() {
        openPage(NavigationConstants.NAVIGATE_TO_CORPORATE_LIST_FRAGMENT, false)
    }

    override fun openPage(entry: NavigationEntry) {
        processNavigation(entry)
    }

    override fun openPage(request: NavigationRequest) {
        navigator.gotoPage(
            FragmentNavigationEntry(
                request.pageId,
                request.bundle,
                request.addToStack
            )
        )
    }

    override fun openPage(pageId: Int, args: Bundle, addToStack: Boolean, forceReload: Boolean) {
        openPage(NavigationRequest(pageId, args, addToStack, forceReload))
    }


    protected fun processNavigation(entry: NavigationEntry?) {
        entry ?: return
        when (entry) {
            is ActivityNavigationEntry -> {
                activity.navigate(entry)
            }
            is FragmentNavigationEntry -> {
                /**
                 * Setting isAddToStack to false if currentFragment is null,
                 * This prevents crash for on first fragment loaded on activity
                 */
                val navEntry = if (navigationHelper.currentFragment == null) {
                    entry.copy(isAddToStack = false)
                } else entry
                navigator.gotoPage(navEntry)
            }
        }
    }

    private fun AppCompatActivity.navigate(entry: ActivityNavigationEntry?) {
        entry ?: return
        val intent = Intent(this, entry.activity).apply {
            putExtras(entry.bundle)
        }
        val requestCode = entry.requestCode
        if (requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }
    }

    override fun openPage(pageId: Int, addToStack: Boolean) {
        openPage(pageId, Bundle.EMPTY, addToStack)
    }

    override fun pageNotAccessible() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
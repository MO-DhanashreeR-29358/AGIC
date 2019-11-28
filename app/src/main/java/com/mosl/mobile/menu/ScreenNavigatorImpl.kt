package com.mosl.mobile.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mosl.annotations.ActivityScoped
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.ClientCode
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.helper.CustomNavigator
import com.mosl.navigator.FragmentNavigationEntry
import com.mosl.navigator.NavigationEntry
import com.mosl.navigator.NavigationHelper
import com.mosl.navigator.NavigationRequest
import com.mosl.navigator.Navigator
import javax.inject.Inject

@ActivityScoped
class ScreenNavigatorImpl  @Inject constructor(activity: AppCompatActivity,
userManager: UserManager,
navigationHelper: NavigationHelper,
@param:ClientCode
protected val cCode: StringPreference,
customNavigator: CustomNavigator,
navigator: Navigator
) : ScreenNavigatorInternal(activity, userManager, navigationHelper, cCode,
customNavigator, navigator) {
  override fun goBack() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun goBack(result: Bundle) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun openHome() {
    openPage(NavigationConstants.NAVIGATE_TO_DEMO, false)
  }

  override fun openPage(entry: NavigationEntry) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun openPage(request: NavigationRequest) {
    navigator.gotoPage(FragmentNavigationEntry(request.pageId, request.bundle, request.addToStack))
  }

  override fun openPage(pageId: Int, args: Bundle, addToStack: Boolean, forceReload: Boolean) {
    openPage(NavigationRequest(pageId, args, addToStack,forceReload ))
  }

  override fun openPage(pageId: Int, addToStack: Boolean) {
    openPage(pageId, Bundle.EMPTY, addToStack)  }

  override fun pageNotAccessible() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
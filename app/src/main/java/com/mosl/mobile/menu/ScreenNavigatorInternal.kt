package com.mosl.mobile.menu

import androidx.appcompat.app.AppCompatActivity
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.helper.CustomNavigator
import com.mosl.navigator.NavigationHelper
import com.mosl.navigator.Navigator

abstract class ScreenNavigatorInternal(protected val activity: AppCompatActivity,
protected val userManager: UserManager,
protected val navigationHelper: NavigationHelper,
protected val clientCode: StringPreference,
protected val customNavigator: CustomNavigator,
protected val navigator: Navigator
) : ScreenNavigator {


}
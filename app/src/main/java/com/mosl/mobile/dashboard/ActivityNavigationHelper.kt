package com.mosl.mobile.dashboard

import android.content.Intent
import android.os.Bundle
import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.menu.ScreenNavigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class ActivityNavigationHelper @Inject constructor(
    internal val screenNavigator: ScreenNavigator
//    customNavigator: CustomNavigatorImpl,
//    internal val urlNavigator: UrlNavigator
) : Disposable {
  private val disposable: CompositeDisposable = CompositeDisposable()
/*  private var pendingMenuAction: AppMenu? = null

  init {
    disposable.add(customNavigator)
  }*/

  fun loadIntent(intent: Intent?) {
    intent ?: return
    Timber.d("Intent Received %s ", intent.toString())
    /* if (isGamoogaNotification(intent) && intent.data == null) {
      loadFragment(NavigationConstants.NAVIGATE_TO_NOTIFICATION)
    } else {
      intent.logActionFromAppShortcut()
      when {
        intent.data != null -> loadUri(intent.data)
        intent.extras.getPageId() == -1 -> screenNavigator.openHome()
        else -> intent.extras?.setupFragment()
      }
    }*/when {
      intent.extras.getPageId() == -1 -> screenNavigator.openHome()
      else -> intent.extras?.setupFragment()
    }
  }

//  fun loadUri(uri: Uri?) {
//    uri ?: return
//    urlNavigator.openUri(uri)
//  }
//
//  fun onMenuClicked(appMenu: AppMenu) {
//    if (appMenu.enabled) {
//      pendingMenuAction = appMenu
//    } else {
//      screenNavigator.pageNotAccessible()
//    }
//  }
//
//  fun handlePendingMenuAction() {
//    pendingMenuAction?.let {
//      loadFragment(it.pageId)
//    }
//    pendingMenuAction = null
//  }

  fun loadFragment(@NavigationConstants pageId: Int) {
    screenNavigator.openPage(pageId)
  }

  override fun isDisposed(): Boolean {
    return disposable.isDisposed
  }

  override fun dispose() {
    disposable.dispose()
  }

/*  private fun Intent.logActionFromAppShortcut() {
    if (getBooleanExtra(ShortcutConstants.IS_FROM_SHORTCUT, false)) {
      val customEvent = CustomEvent("AppShortcuts")
      customEvent.putCustomAttribute("Type", getStringExtra(ShortcutConstants.PAGE_TAG))
      logCustomEvent(customEvent)
    }
  }*/

  private fun Bundle.setupFragment(addToStack: Boolean = false) {
    screenNavigator.openPage(getPageId(), this, addToStack)
  }

/*  private fun isGamoogaNotification(intent: Intent): Boolean {
    return intent.getStringExtra(ARG_NOTIFICATION_CATEGORY)
        ?.equals(GamoogaNotification, ignoreCase = true)
        ?: false
  }*/

  companion object {
    internal fun Bundle?.getPageId(): Int {
      return this?.getInt(MOSLCommonActivity.ARG_PAGE_ID, NavigationConstants.NAVIGATE_TO_NONE)
          ?: NavigationConstants.NAVIGATE_TO_NONE
    }
/*
    //Notification
    const val ARG_NOTIFICATION_CATEGORY = "category"

    //Gamooga Constants
    const val GamoogaNotification = "GamoogaNotification"*/
  }
}
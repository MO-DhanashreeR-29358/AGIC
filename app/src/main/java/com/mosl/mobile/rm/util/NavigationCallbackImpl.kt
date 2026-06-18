package com.mosl.mobile.rm.util

import androidx.fragment.app.Fragment
import com.mosl.annotations.ActivityScoped
import com.mosl.navigator.NavigationCallback
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by snehaganapuram on 02-01-2018.
 */
@ActivityScoped
class NavigationCallbackImpl @Inject constructor() : NavigationCallback {
  private val pageStack = ArrayList<String>()

  override fun onPageOpened(fragment: Fragment, addedToStack: Boolean) {
    setCrashlyticsInfo(fragment, addedToStack)
   // eventsHelper.pageOpened(fragment)
  //  fragment.logPageEvents(fragment.arguments)
  }

  override fun onBackNavigation() {
    removeFromStack()
  }

  private fun removeFromStack() {
    if (pageStack.size > 0) {
      pageStack.removeAt(pageStack.size - 1)
    }
    Timber.d("Current Stack : %s", pageStack.toString())
 //   Crashlytics.setString("PageStack", pageStack.toString())
  }

  private fun updatePageStack(entry: String, addStack: Boolean) {
    if (addStack) {
      pageStack.add(entry)
    } else {
      pageStack.clear()
      pageStack.add(entry)
    }
    Timber.d("Current Stack : %s", pageStack.toString())
    //Crashlytics.setString("PageStack", pageStack.toString())
  }

  private fun setCrashlyticsInfo(fragment: Fragment, addStack: Boolean) {
    updatePageStack(fragment.toString(), addStack)
  //  Crashlytics.setString("PageToOpen", fragment.toString())
    fragment.arguments?.let {
   //   Crashlytics.setString("PageData", it.toString())
    }
  }
}

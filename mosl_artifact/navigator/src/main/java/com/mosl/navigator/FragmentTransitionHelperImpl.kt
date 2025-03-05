package com.mosl.navigator

import androidx.fragment.app.FragmentManager
import com.mosl.annotations.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by lalkishore on 10/3/2016.
 */
@ActivityScoped
class FragmentTransitionHelperImpl @Inject constructor(
    private val navHelper: NavigationHelper
) : FragmentTransitionHelper() {
  /**
   * FragmentManager instance
   */
  private val fragmentManager: FragmentManager = navHelper.fragmentManager

  override fun storeMessage(message: FragmentNavigationEntry): Boolean {
    return true
  }

  override fun processMessage(message: FragmentNavigationEntry) {
    if (fragmentManager.isDestroyed) {
      Timber.d("Activity has been destroyed...")
      return
    }
    if (message.isGoBack) {
      navHelper.goBackOnce(message.args)
    } else {
      navHelper.addFragment(message)
    }
  }
}

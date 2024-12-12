package com.mosl.app.coreui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mosl.app.coreui.adapters.ViewPagerAdapter.Helper
import com.mosl.app.coreui.base.BaseFragment

class ViewPagerIconAdapter(
    fm: FragmentManager,
    private val mContext: Context,
    private val helper: Helper,
    private var tabs: List<TabIndicator> = emptyList()
) : SmartFragmentPagerAdapter(fm), PagerFragmentProvider {

  fun setTabs(tabs: List<TabIndicator>) {
    this.tabs = tabs
  }

  /**
   * This method should never be called by Hand Written Code
   * instead use getFragmentAt(position)
   */
  override fun getItem(position: Int): Fragment {
    return helper.getItem(position)
  }

  override fun getCount(): Int {
    return tabs.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return mContext.getString(tabs[position].label)
  }

  override fun getFragmentAt(position: Int): BaseFragment? {
    val fragment = getRegisteredFragment(position)
    return if (fragment is BaseFragment) {
      fragment
    } else null
  }
}
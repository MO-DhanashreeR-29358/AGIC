package com.mosl.volunteer.core

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.mosl.volunteer.helper.PagerLoaderManager

open class MOSLPagerItemFragment : MOSLCommonFragment() {
  private var loadData: Boolean = false
  private var loaderManager: PagerLoaderManager? = null

  private fun setLoaderManager(manager: PagerLoaderManager) {
    this.loaderManager = manager
  }

  fun loadData(): Boolean {
    return loadData
  }

  fun setLoadData(loadData: Boolean) {
    this.loadData = loadData
  }

  fun showProgressBar() {
    loaderManager?.onShowProgress(this)
  }

  fun hideProgressBar() {
    loaderManager?.onHideProgress(this)
  }

  override fun toggleProgressBar(inProgress: Boolean) {
    if (inProgress) {
      showProgressBar()
    } else {
      hideProgressBar()
    }
  }

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initManagers()
    if (savedInstanceState != null) {
      loadData = savedInstanceState.getBoolean("loadData")
    }
  }

  fun initManagers() {
    val parentFragment = parentFragment
    if (parentFragment is PagerLoaderManager) {
      setLoaderManager(parentFragment)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.putBoolean("loadData", loadData)
    super.onSaveInstanceState(outState)
  }

  @Deprecated("Use showProgressBar()")
  override fun showProgress() {
    showProgressBar()
  }

  @Deprecated("Use hideProgressBar()")
  override fun hideProgress() {
    hideProgressBar()
  }
}

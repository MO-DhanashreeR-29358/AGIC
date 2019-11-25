package com.mosl.mobile.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.nav.DebugConstants.NAVIGATE_TO_LOGIN

class SplashActivity : LeanBaseActivity() {
  lateinit var binding: ActivitySplashScreenBinding

  override fun getLayout(): Int {
    return R.layout.activity_splash_screen
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySplashScreenBinding.bind(findViewById(R.id.splash_root))
    checkNetwork()
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(LocaleManager.withLocale(base))
  }

  private fun checkNetwork() {
    if (this.isNetworkConnected()) {
      initializeApp()
    } else {
      // No network
    }
  }


  private fun initializeApp() {
    openDashboard()
  }

  internal fun openDashboard() {
    val intent = Intent(this, MOSLCommonActivity::class.java).apply{
      putExtra(MOSLCommonActivity.ARG_PAGE_ID, NAVIGATE_TO_LOGIN)
    }
    startActivity(intent)
    finish()
  }
}
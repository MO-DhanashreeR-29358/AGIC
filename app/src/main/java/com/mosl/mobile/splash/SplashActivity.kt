package com.mosl.mobile.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.nav.DebugConstants
import com.mosl.mobile.newsletter.NewsLetterActivity
import javax.inject.Inject

class SplashActivity : LeanBaseActivity() {
    lateinit var binding: ActivitySplashScreenBinding

    override fun getLayout(): Int {
        return R.layout.activity_splash_screen
    }

    @Inject
    lateinit var userManager: UserManager

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
        if (userManager.isLoggedIn()) {
            val intent = Intent(this, MOSLCommonActivity::class.java)
                .apply {
                    putExtra(MOSLCommonActivity.ARG_PAGE_ID, DebugConstants.NAVIGATE_TO_SCHEDULE)
                }
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, NewsLetterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
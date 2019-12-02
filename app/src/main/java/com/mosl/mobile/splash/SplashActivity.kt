package com.mosl.mobile.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.core.utils.showConfirmation
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.nav.DebugConstants
import com.mosl.mobile.newsletter.NewsLetterActivity
import javax.inject.Inject

class SplashActivity : LeanBaseActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //3 seconds
    private var count = 0


    override fun getLayout(): Int {
        return R.layout.activity_splash_screen
    }

    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.withLocale(base))
    }

    private fun checkNetwork() {
        if (this.isNetworkConnected()) {
            initializeApp()
        } else {
            // No network

            if (count < 2) {
                count++
                showConfirmation(
                    message = "Application needs network to proceed",
                    cancelable = true,
                    title = getString(R.string.alert_dialog_title),
                    positiveButton = R.string.button_label_retrieve,
                    negativeButton = R.string.button_label_exit
                )
                { positiveClicked ->
                    if (positiveClicked) {
                        checkNetwork()
                    } else {
                        finish()
                    }
                    Unit
                }
            } else {
                actionableAlert(
                    message = "Application needs network to proceed",
                    cancelable = false,
                    buttonText = R.string.button_label_exit
                ) {
                    finish()
                }
            }

        }
    }


    private fun initializeApp() {
        openDashboard()
    }

    internal fun openDashboard() {
        if (userManager.isLoggedIn()) {
            val intent = Intent(this, MOSLCommonActivity::class.java)
                .apply {
                    putExtra(MOSLCommonActivity.ARG_PAGE_ID, NavigationConstants.NAVIGATE_TO_SCHEDULE)
                }
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, NewsLetterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            checkNetwork()

        }
    }
}
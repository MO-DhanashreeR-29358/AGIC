package com.mosl.mobile.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.core.utils.showConfirmation
import com.mosl.mobile.R
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.coreui.core.MSFDialog
import com.mosl.mobile.coreui.core.MSFDialog.Action.OK

import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.newsletter.NewsLetterActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //3 seconds
    private var count = 0


//    override fun getLayout(): Int {
//        return R.layout.activity_splash_screen
//    }

    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
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
                    message = getString(R.string.msg_application_needs_network_to_proceed),
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
                showActionableAlert(
                    message =  getString(R.string.msg_application_needs_network_to_proceed),
                    buttonText = getString(R.string.button_label_exit)
                )
            }

        }
    }

    private fun showActionableAlert(
        message: String,
        buttonText: String,
        cancelable: Boolean = false
    ) {
        MSFDialog.alertDialog(
            this@SplashActivity,
            0,
            resources.getString(R.string.main_app_name),
            message,
            buttonText,
            cancelable
        ) { action, _ ->
            if (action == OK) {
                finish()
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
                    putExtra(
                        MOSLCommonActivity.ARG_PAGE_ID,
                        NavigationConstants.NAVIGATE_TO_SCHEDULE
                    )
                }
            startActivity(intent)
            finish()
        } else if (!userManager.isLoggedIn() && userManager.isActive()) {
            val intent = Intent(this, LoginActivity::class.java)

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
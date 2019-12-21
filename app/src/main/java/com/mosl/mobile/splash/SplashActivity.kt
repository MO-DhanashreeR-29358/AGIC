package com.mosl.mobile.splash

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.core.utils.showConfirmation
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.BuildConfig
import com.mosl.mobile.R
import com.mosl.mobile.annotations.ImageSplashUrl
import com.mosl.mobile.annotations.RemoteConfigData
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.coreui.core.MSFDialog
import com.mosl.mobile.data.RemoteConfigModel
import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.newsletter.NewsLetterActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


class SplashActivity : LeanBaseActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    private var mDelayHandler: Handler = Handler()
    private val SPLASH_DELAY: Long = 2000 //2 seconds
    private var count = 0


    override fun getLayout(): Int {
        return R.layout.activity_splash_screen
    }

    @Inject
    lateinit var userManager: UserManager
    @Inject
    lateinit var gson: Gson
    @Inject
    @field:ImageSplashUrl
    lateinit var splashImgUrl: StringPreference

    @Inject
    @field:RemoteConfigData
    lateinit var remoteCongigData: StringPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivitySplashScreenBinding.bind(findViewById(R.id.splash_layout))

        //Initialize the Handler

        //Navigate with delay

//        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        val splashImageUrl = getSplashImageUrl()
        loadSplashImage(splashImageUrl)

        mDelayHandler.postDelayed(
            { checkNetwork() },
            (if (splashImageUrl == null) 0 else SPLASH_DELAY).toLong()
        )
    }


    private fun getSplashImageUrl(): String? {
        return if (!splashImgUrl.get().isEmpty()) {
            splashImgUrl.get()
        } else {
            null
        }
    }

    private fun loadSplashImage(imageUrl: String?) {
        if (imageUrl != null) {
            Glide.with(this)
                .load(imageUrl)
                .into(binding.ivSplash)
        }
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.withLocale(base))
    }

    private fun checkNetwork() {
        if (this.isNetworkConnected()) {
//            initializeApp()
            checkVersion()
        } else {
            if (userManager.isLoggedIn()) {
                Toast.makeText(
                    this,
                    R.string.msg_application_needs_network_to_proceed,
                    Toast.LENGTH_LONG
                ).show()
                initializeApp()
            } else {
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
                    actionableAlert(
                        message = getString(R.string.msg_application_needs_network_to_proceed),
                        cancelable = false,
                        buttonText = R.string.button_label_exit
                    ) {
                        finish()
                    }
                }
            }
        }
    }


    private fun checkVersion() {

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val agicInit =
                        remoteConfig.getString(getString(R.string.agic_init))
                    if (agicInit != null && !agicInit.isEmpty()) {
                        val remoteConfigModel: RemoteConfigModel =
                            gson.fromJson(agicInit, RemoteConfigModel::class.java)
                        splashImgUrl.set(remoteConfigModel.splash)
                        remoteCongigData.set(agicInit)
                        if (BuildConfig.VERSION_NAME.equals(remoteConfigModel.android.version)) {
                            initializeApp()
                        } else {

                            if (remoteConfigModel.android.isMandatory) {

                                MSFDialog.alertDialog(this@SplashActivity,
                                    0,
                                    resources.getString(R.string.main_app_name),
                                    remoteConfigModel.android.releaseNote,
                                    UPDATE,
                                    false,
                                    object : MSFDialog.DialogListener {
                                        override fun alertDialogAction(
                                            action: MSFDialog.Action,
                                            vararg data: Any
                                        ) {
                                            if (action == MSFDialog.Action.OK) {
                                                val i = Intent(
                                                    Intent.ACTION_VIEW
                                                )
                                                i.data =
                                                    Uri.parse(remoteConfigModel.android.appLink)
                                                startActivity(i)
                                                finish()
                                            }
                                        }
                                    })
                            } else {
                                MSFDialog.alertDialog(this@SplashActivity,
                                    0,
                                    resources.getString(R.string.main_app_name),
                                    remoteConfigModel.android.releaseNote,
                                    UPDATE,
                                    CONTINUE,
                                    false,
                                    object : MSFDialog.DialogListener {
                                        override fun alertDialogAction(
                                            action: MSFDialog.Action,
                                            vararg data: Any
                                        ) {
                                            if (action == MSFDialog.Action.OK) {
                                                val i = Intent(
                                                    Intent.ACTION_VIEW
                                                )
                                                i.data =
                                                    Uri.parse(remoteConfigModel.android.appLink)
                                                startActivity(i)
                                                finish()
                                            } else if (action === MSFDialog.Action.CANCEL) {
                                                initializeApp()
                                            }
                                        }
                                    })
                            }
                        }
                    }
                }
            }
    }

    private fun initializeApp() {
        openDashboard()
    }

    override fun clearWindowBackground(): Boolean {
        return false
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
        } else if (userManager.isActive()) {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, NewsLetterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    companion object {
        val UPDATE = "Update"
        val CONTINUE = "Continue"
        val GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=com.mosl.tabapp"
    }

//    internal val mRunnable: Runnable = Runnable {
//        if (!isFinishing) {
//            if (splashImgUrl.isNotSet) {
//                retrieveFromRemoteConfig()
//            } else
//                Glide.with(this)
//                    .load(splashImgUrl.get())
//                    .into(binding.ivSplash)
//            checkNetwork()
//
////            checkNetwork()
//
//        }
//    }
}
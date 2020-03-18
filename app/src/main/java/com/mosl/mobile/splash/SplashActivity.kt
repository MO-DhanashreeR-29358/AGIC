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
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.coreui.core.MSFDialog
import com.mosl.mobile.dashboard.ActivityNavigationHelper.Companion.ARG_NOTIFICATION_CATEGORY
import com.mosl.mobile.data.RemoteConfigModel
import com.mosl.mobile.databinding.ActivitySplashScreenBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.newsletter.NewsLetterActivity
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject


class SplashActivity : LeanBaseActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    private var mDelayHandler: Handler = Handler()
    private val SPLASH_DELAY: Long = 2000 //2 seconds
    private var count = 0
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
    lateinit var notificationIntent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivitySplashScreenBinding.bind(findViewById(R.id.splash_layout))
        val splashImageUrl = getSplashImageUrl()
        loadSplashImage(splashImageUrl)
        setupView()
        notificationIntent=intent
        mDelayHandler.postDelayed(
            { checkNetwork() },
            (if (splashImageUrl == null) 0 else SPLASH_DELAY).toLong()
        )
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash_screen
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

    fun setupView() {
        binding.txtAppInfo.text = java.lang.String.format(
            Locale.ENGLISH, "App Version : %s(%d)", BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE
        )

    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.withLocale(base))
    }

    private fun checkNetwork() {
        if (this.isNetworkConnected()) {
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
            if (notificationIntent.isNotification()) {
                val intent = Intent(this@SplashActivity, MOSLCommonActivity::class.java)
                intent.putExtra(
                    ARG_NOTIFICATION_CATEGORY,
                    notificationIntent.getStringExtra(ARG_NOTIFICATION_CATEGORY)
                )
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, MOSLCommonActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else if (userManager.isActive()) {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this@SplashActivity, NewsLetterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun Intent.isNotification(): Boolean {
        val category: String? = getStringExtra(ARG_NOTIFICATION_CATEGORY)
        return category != null
    }

    companion object {
        val UPDATE = "Update"
        val CONTINUE = "Continue"
    }
}
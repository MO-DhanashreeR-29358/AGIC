package com.mosl.mobile.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mosl.app.core.events.DataAction
import com.mosl.app.coreui.base.BaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityLoginBinding
import com.mosl.mobile.nav.DebugConstants
import com.mosl.navigator.AppTitle
import com.mosl.navigator.AuthorizationState
import com.mosl.navigator.FragmentNavigationEntry
import com.mosl.navigator.Navigator
import io.reactivex.Observable

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : BaseActivity(), Navigator {

    lateinit var binding: ActivityLoginBinding

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityLoginBinding.bind(findViewById(R.id.constrainLaout))

        binding.btnlogin.setOnClickListener(View.OnClickListener { openDashboard() })

    }

    internal fun openDashboard() {
        val intent = Intent(this, MOSLCommonActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, DebugConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }

    fun toggleProgressBar(inProgress: Boolean) {
        if (inProgress) {
            showProgress()
        } else {
            hideProgress()
        }
    }


    override fun authorizationDialog(listener: (AuthorizationState) -> Unit) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearTitles() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainerViewId(): Int {
        return 0
    }

    override fun getFragment(pageId: Int): Fragment? {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun gotoPage(fragmentEntry: FragmentNavigationEntry) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideAppTitle() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setAppTitle(
        page: String,
        appTitle: AppTitle?
    ) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

}

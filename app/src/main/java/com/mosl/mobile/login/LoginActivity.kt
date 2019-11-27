package com.mosl.mobile.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityLogin2Binding
import com.mosl.mobile.nav.DebugConstants

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : LeanBaseActivity() {

    lateinit var binding: ActivityLogin2Binding

    override fun getLayout(): Int {
        return R.layout.activity_login2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_login2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityLogin2Binding.bind(findViewById(R.id.constrainLaout))

        binding.btnlogin.setOnClickListener(View.OnClickListener { openDashboard() })

    }

    internal fun openDashboard() {
        val intent = Intent(this, MOSLCommonActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, DebugConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }

}

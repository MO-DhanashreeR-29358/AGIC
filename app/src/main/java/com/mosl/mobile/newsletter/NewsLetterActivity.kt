package com.mosl.mobile.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityNewsLetterBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.nav.DebugConstants

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NewsLetterActivity : LeanBaseActivity() {

    lateinit var binding: ActivityNewsLetterBinding

    override fun getLayout(): Int {
        return R.layout.activity_news_letter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityNewsLetterBinding.bind(findViewById(R.id.rrTCT))

        binding.skipToLogin.setOnClickListener(View.OnClickListener { openDashboard() })

    }

    internal fun openDashboard() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, DebugConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }

}

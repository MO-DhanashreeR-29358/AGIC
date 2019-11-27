package com.mosl.mobile.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityNewsLetterBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.nav.DebugConstants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NewsLetterActivity : LeanBaseActivity() {

    lateinit var binding: ActivityNewsLetterBinding

    lateinit var section: Section


    override fun getLayout(): Int {
        return R.layout.activity_news_letter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityNewsLetterBinding.bind(findViewById(R.id.rrTCT))

        binding.skipToLogin.setOnClickListener(View.OnClickListener { openDashboard() })

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(baseContext)
            setHasFixedSize(true)
            adapter = groupAdapter
        }

        val itemData = listOf(
            NewsLetterHeader(
                url = "https://app.slack.com/client/TGVG31LQ4/CQEF9E7EX",
                description = "Aug 30, 2017 - According to the Android Docs",
                image = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            NewsLetterHeader(
                url = "Animate the transition between fragments",
                description = "Add this method to replace fragments with Animations:",
                image = "Are attitudes changing globally towards 'owning' a vehicle?"
            )
        )
        for (model in itemData) {
            section.add(NewsLetterListItem(model))
        }


    }


    internal fun openDashboard() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, DebugConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }
}
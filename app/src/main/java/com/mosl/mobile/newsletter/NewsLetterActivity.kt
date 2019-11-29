package com.mosl.mobile.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView.OnGroupExpandListener
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityNewsLetterBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.nav.DebugConstants


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NewsLetterActivity : LeanBaseActivity() {

    lateinit var listAdapter: ExpandableFaqsListAdapter
    lateinit var binding: ActivityNewsLetterBinding

    override fun getLayout(): Int {
        return R.layout.activity_news_letter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLetterBinding.bind(findViewById(R.id.relativeLayoutMain))

        binding.skipToLogin.setOnClickListener(View.OnClickListener { openDashboard() })

        setupRecyclerView()
    }

    var faqsListParent: ArrayList<NewsLetterHeader>? = null
    var faqsListChild: ArrayList<NewsLetterHeader>? = null

    private fun setupRecyclerView() {

        faqsListParent = ArrayList<NewsLetterHeader>()
        faqsListChild = ArrayList<NewsLetterHeader>()

        faqsListParent!!.add(
            NewsLetterHeader(
                url = "https://app.slack.com/client/TGVG31LQ4/CQEF9E7EX",
                description = "Not the answer you're looking for? Browse other questions tagged Are attitudes changing globally towards 'owning' a vehicle? Aug 30, 2017 - According to the Android Docs",
                image = ""
            )
        )

        faqsListParent!!.add(
            NewsLetterHeader(
                url = "Animate the transition between fragments",
                description = "Are attitudes changing globally towards 'owning' a vehicle? Also you can simple use the withOnlyOneExpandedItem() which is in FastAdapter Add this method to replace fragments with Animations:",
                image = ""
            )
        )

        faqsListChild!!.add(
            NewsLetterHeader(
                url = "Animate the transition between fragments",
                description = "Add this method to replace fragments with Animations:",
                image = "Are attitudes changing globally towards 'owning' a vehicle?"
            )
        )

        listAdapter = ExpandableFaqsListAdapter(baseContext, faqsListParent, faqsListChild)

        binding.recyclerView.setAdapter(listAdapter)

        binding.recyclerView.setOnGroupExpandListener(object : OnGroupExpandListener {
            internal var previousGroup = -1

            override fun onGroupExpand(groupPosition: Int) {
                if (groupPosition != previousGroup)
                    binding.recyclerView.collapseGroup(previousGroup)
                previousGroup = groupPosition
            }
        })
    }


    internal fun openDashboard() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, NavigationConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }
}
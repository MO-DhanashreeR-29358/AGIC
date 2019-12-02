package com.mosl.mobile.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView.OnGroupExpandListener
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.R
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ActivityNewsLetterBinding
import com.mosl.mobile.domain.newsletter.NewsLetterResponseInfo
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo
import com.mosl.mobile.login.LoginActivity
import io.reactivex.Observable
import javax.inject.Inject


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class NewsLetterActivity : LeanBaseActivity(), NewsLetterView {

    lateinit var listAdapter: ExpandableNewsLetterAdapter
    lateinit var binding: ActivityNewsLetterBinding

    @Inject
    lateinit var presenter: NewsLetterPresenter

    override fun getLayout(): Int {
        return R.layout.activity_news_letter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLetterBinding.bind(findViewById(R.id.relativeLayoutMain))

        binding.skipToLogin.setOnClickListener(View.OnClickListener { openDashboard() })

        presenter.start()
//        presenter.onFetchCalled()
    }


    internal fun openDashboard() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, NavigationConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }

    override fun onSkipClicked(): Observable<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderSkip(uiModel: UiModel<Unit>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderBrochureData(uiModel: UiModel<List<NewsLetterResponseItemInfo>>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
            error.showOnFailurePopup()
        }

        uiModel.onSuccess { data ->

            listAdapter = ExpandableNewsLetterAdapter(baseContext, data)

            binding.expandableList.setAdapter(listAdapter)

            binding.expandableList.setOnGroupExpandListener(object : OnGroupExpandListener {
                internal var previousGroup = -1

                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup)
                        binding.expandableList.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                }
            })
        }
    }

    fun toggleProgressBar(inProgress: Boolean) {
        if (inProgress) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    fun Throwable.showOnFailurePopup() {
        val errorOnFailure = this.getErrorOnFailure()
        alert(message = errorOnFailure.getResolvedMessage(applicationContext))
    }

    override fun isDataEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataAction(): Observable<DataAction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }


    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }
}
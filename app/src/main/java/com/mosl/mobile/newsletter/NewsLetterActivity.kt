package com.mosl.mobile.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView.OnGroupExpandListener
import com.jakewharton.rxbinding3.view.clicks
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
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.login.UserManagerImpl
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
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

    @Inject
    lateinit var userManager: UserManagerImpl
    private val dataActionSubject = PublishSubject.create<DataAction>()

    override fun getLayout(): Int {
        return R.layout.activity_news_letter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLetterBinding.bind(findViewById(R.id.relativeLayoutMain))

        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }


    internal fun openDashboard() {
        userManager.saveNewsLetterState(true)
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(MOSLCommonActivity.ARG_PAGE_ID, NavigationConstants.NAVIGATE_TO_SCHEDULE)
        }
        startActivity(intent)
        finish()
    }

    override fun onSkipClicked(): Observable<Unit> {
        return binding.skipToLogin.clicks()
    }

    override fun renderBrochureData(uiModel: UiModel<NewsLetterResponseInfo>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
            error.showOnFailurePopup()
        }

        uiModel.onSuccess { data ->

            if (data.IsActive)
                binding.skipToLogin.visibility = View.VISIBLE
            else
                binding.skipToLogin.visibility = View.GONE

                listAdapter = ExpandableNewsLetterAdapter(data.Data)

                binding.expandableList.setAdapter(listAdapter)

                binding.expandableList.setOnGroupExpandListener(object : OnGroupExpandListener {
                    var previousGroup = -1

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

    override fun renderSkip(uiModel: UiModel<Unit>) {
        uiModel.onSuccess { _ -> openDashboard() }
    }

    fun Throwable.showOnFailurePopup() {
        val errorOnFailure = this.getErrorOnFailure()
        alert(message = errorOnFailure.getResolvedMessage(applicationContext))
    }

    override fun isDataEmpty(): Boolean {
        return true
    }


    override fun onDataAction(): Observable<DataAction> {
        return dataActionSubject
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }


    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }
}
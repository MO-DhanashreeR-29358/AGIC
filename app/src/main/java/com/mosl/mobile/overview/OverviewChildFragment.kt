package com.mosl.mobile.overview


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentOverviewChildBinding
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.mobile.overviewpager.OverviewPagerTabChangeEvent
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class OverviewChildFragment : MOSLCommonFragment(), OverviewChildContract {

    lateinit var binding: FragmentOverviewChildBinding

    @Inject
    lateinit var presenter: OverviewChildPresenter
    lateinit var section: DataManagerSection

    private var publishSubject = PublishSubject.create<OverviewPagerTabChangeEvent>()

    override fun getLayout(): Int {
        return R.layout.fragment_overview_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewChildBinding.bind(view)
        setupRecyclerView()
        presenter.start()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvOverviewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    fun setSubject(tabPublishSubject: PublishSubject<OverviewPagerTabChangeEvent>) {
        publishSubject = tabPublishSubject

    }

    override fun onTabClicked(): Observable<OverviewPagerTabChangeEvent.TabClick> {
        return publishSubject.ofType()
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ): OverviewChildFragment {
            val fragment = OverviewChildFragment()

            return fragment
        }
    }

    override fun renderPage(uiModel: UiModel<List<OverviewPagerResponseDayItemInfo>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            section.clearContent()

            for (model in it) {
                section.add(ItemOverview(model))
            }
        }
    }
}

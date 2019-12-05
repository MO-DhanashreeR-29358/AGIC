package com.mosl.mobile.panel


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
import com.mosl.mobile.databinding.FragmentPanelChildBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelListDayItemData
import com.mosl.mobile.panelpager.PanelTabChangeEvent
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelChildFragment : MOSLCommonFragment(), PanelChildView {
    @Inject
    lateinit var presenter: PanelPresenter
    lateinit var binding: FragmentPanelChildBinding
    lateinit var section: DataManagerSection

    private var publishSubject = PublishSubject.create<PanelTabChangeEvent>()

    override fun getLayout(): Int {
        return R.layout.fragment_panel_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelChildBinding.bind(view)
        setupRecyclerView()
        presenter.start()
//        dataActionSubject.onNext(DataAction.Fetch)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvPanelList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }


    }

    fun setSubject(tabPublishSubject: PublishSubject<PanelTabChangeEvent>) {
        publishSubject = tabPublishSubject

    }

    override fun panelDayList(): ArrayList<PanelListDayItemData>? {
        return arguments?.getParcelableArrayList(PanelFragment.PANEL_DAY_LIST)
    }

    override fun renderPage(uiModel: UiModel<List<PanelDayResponse>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess { panelListDayData ->
            section.clearContent()
            for (model in panelListDayData) {
                section.add(ItemPanelList(model, context!!))
            }
        }

    }

    override fun onTabClicked(): Observable<PanelTabChangeEvent.TabClick> {
        return publishSubject.ofType()
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ): PanelChildFragment {
            val fragment = PanelChildFragment()

            return fragment
        }
    }
}

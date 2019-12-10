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
import com.mosl.mobile.core.MOSLPagerItemFragment
import com.mosl.mobile.databinding.FragmentPanelChildBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelListDayItemData
import com.mosl.mobile.panelpager.PanelTabChangeEvent
import com.mosl.shared.util.addToCalendart
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelChildFragment : MOSLPagerItemFragment(), PanelChildView {
    @Inject
    lateinit var presenter: PanelChildPresenter
    lateinit var binding: FragmentPanelChildBinding
    lateinit var section: DataManagerSection

    lateinit var publishSubject: Observable<UiModel<List<PanelDayResponse>>>
    private var publishSubjectItem = PublishSubject.create<PanelUiEvent>()

    override fun getLayout(): Int {
        return R.layout.fragment_panel_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelChildBinding.bind(view)
        setupRecyclerView()
        publishSubject = (parentFragment as? PanelFragment)?.tabPublishSubject ?: Observable.empty()
        presenter.start()
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
                section.add(ItemPanelList(model, context!!, publishSubjectItem))
            }
        }

    }

    override fun onTabClicked(): Observable<UiModel<List<PanelDayResponse>>> {
        return publishSubject.ofType()
    }

    override fun renderItemToCalender(panelDayResponse: PanelDayResponse) {
        addToCalendart(
            context!!,
            panelDayResponse.cRepresentiveName,
            panelDayResponse.speakerIntroduction,
            "",
            panelDayResponse.representativeStarttime,
            panelDayResponse.representativeEndtime
        )
    }

    override fun onItemClicked(): Observable<PanelUiEvent.ItemClick> {
        return publishSubjectItem.ofType()
    }

}

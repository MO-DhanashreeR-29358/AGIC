package com.mosl.mobile.panel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.core.events.DataAction
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentPanelChildBinding
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelListDayItemData

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelChildFragment : MOSLCommonFragment(), PanelChildView {
    @Inject
    lateinit var presenter: PanelPresenter
    lateinit var binding: FragmentPanelChildBinding
    lateinit var section: DataManagerSection


    override fun getLayout(): Int {
        return R.layout.fragment_panel_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelChildBinding.bind(view)
        setupRecyclerView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
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

    override fun panelDayList():ArrayList<PanelListDayItemData>? {
       return arguments?.getParcelableArrayList(PanelFragment.PANEL_DAY_LIST)
    }

    override fun renderPage(panelListDayData:List<PanelListDayItemData>?) {

        if(panelListDayData!!.isNotEmpty()){
            for (model in panelListDayData) {
                section.add(ItemPanelList(model, context!!))
            }
        }

    }
}

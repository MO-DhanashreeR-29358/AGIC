package com.mosl.mobile.panel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentPanelChildBinding

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelChildFragment : MOSLCommonFragment(), PanelChildContract {
    @Inject
    lateinit var presenter: PanelPresenter
    lateinit var binding: FragmentPanelChildBinding
    lateinit var section: Section


    override fun getLayout(): Int {
        return R.layout.fragment_panel_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelChildBinding.bind(view)
        setupRecyclerView()
        //presenter.start()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvPanelList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }

        val itemData = listOf(
            PanelChildData(
                name = "Ms.Nandini Maheshwari",
                time = "10:00 am - 11:00 am",
                designation = "Business Development Head,Uber",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelChildData(
                name = "Mr.Punit Chhatwal",
                time = "11:00 am - 12:00 pm",
                designation = "Ceo,Indian Hotels",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelChildData(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelChildData(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelChildData(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            )
        )
        for (model in itemData) {
            section.add(ItemPanelList(model, context!!))
        }


    }

    override fun renderPage() {

    }
}

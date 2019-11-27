package com.mosl.mobile.panel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentPanelBinding

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelFragment : MOSLCommonFragment(), PanelContract.View {
    @Inject
    lateinit var presenter: PanelPresenter
    lateinit var binding: FragmentPanelBinding
    lateinit var section: Section


    override fun getLayout(): Int {
        return R.layout.fragment_panel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelBinding.bind(view)
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
            PanelDataHeader(
                name = "Ms.Nandini Maheshwari",
                time = "10:00 am - 11:00 am",
                designation = "Business Development Head,Uber",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelDataHeader(
                name = "Mr.Punit Chhatwal",
                time = "11:00 am - 12:00 pm",
                designation = "Ceo,Indian Hotels",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelDataHeader(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelDataHeader(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            ),
            PanelDataHeader(
                name = "Mr.Harshwardhan Jala",
                time = "12:00 pm - 01:00 pm",
                designation = "Teenage Founder & Ceo,Aerobotics7",
                desc = "Are attitudes changing globally towards 'owning' a vehicle?"
            )
        )
        for (model in itemData) {
            section.add(PanelListItem(model))
        }


    }

    override fun renderPage() {

    }
}

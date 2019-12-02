package com.mosl.mobile.overview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentOverviewChildBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder


/**
 * A simple [Fragment] subclass.
 */
class OverviewChildFragment : MOSLCommonFragment(), OverviewChildContract {
    lateinit var binding: FragmentOverviewChildBinding
    lateinit var presenter: OverviewChildPresenter
    lateinit var section: Section
    override fun getLayout(): Int {
        return R.layout.fragment_overview_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewChildBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvOverviewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }

        val itemData = listOf(
            OverviewChildData(
                "Mr.Prasun Basu",
                "Mr.Prasun Basu - President-South Asia ,Nielsen",
                "01:00 pm ",
                "01:50 pm"
            ),
            OverviewChildData(
                "Mr.Saugata Gupta ",
                "Ms.Saugata Gupta-MD, Marico",
                "02:00 pm ",
                "02:50 pm"
            ),
            OverviewChildData(
                "Mr.C S Ghosh",
                "Mr.C S Ghosh -Founder & Chairman, Bandhan Bank",
                "03:00 pm ",
                "03:50 pm"
            ),
            OverviewChildData(
                "Mr.Pradeep Gupta",
                "Mr.Pradeep Gupta - MD ,Axis My India",
                "04:00 pm ",
                "04:50 pm"
            ),
            OverviewChildData(
                "Ms.Nandini Maheshwari",
                "Ms.Nandini Maheshwari ,Business Development Head,Uber",
                "05:00 pm ",
                "05:50 pm"
            )
        )
        for (model in itemData) {
            section.add(ItemOverview(model))
        }


    }

}

package com.mosl.mobile.schedule

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentScheduleBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class ScheduleFragment : MOSLCommonFragment(), ScheduleContract {
    //@Inject
    lateinit var presenter: SchedulePresenter
    private lateinit var binding: FragmentScheduleBinding
    private lateinit var section: Section


    override fun getLayout(): Int {
        return R.layout.fragment_schedule
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvScheduleList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter

        }


        val itemData = listOf(
            ScheduleData(
                "10:00 am - 11:00 am",
                "Ms.Nandini Maheshwari,Business Development Head,Uber",
                "Ms.Nandini Maheshwari",
                "Venue- Ballroom Level"
            ),
            ScheduleData(
                "11:00 am - 12:00 pm",
                "Mr.Punit Chhatwal,Ceo,Indian Hotels",
                "Mr.Punit Chhatwal",
                "Venue- Ballroom Level"
            ),
            ScheduleData(
                "12:00 pm - 01:00 pm",
                "Mr.Harshwardhan Jala,Teenage Founder & Ceo,Aerobotics7",
                "Mr.Harshwardhan Jala",
                "Venue- Ballroom Level"
            ),
            ScheduleData(
                "01:00 pm - 02:00 pm",
                "Mr.Prasun Basu - President-South Asia ,Nielsen",
                "Mr.Prasun Basu",
                "Venue- Ballroom Level"
            ),
            ScheduleData(
                "02:00 pm - 03:00 pm",
                "Mr.C S Ghosh -Founder & Chairman, Bandhan Bank",
                "Mr.C S Ghosh",
                "Venue- Ballroom Level"
            )
        )
        for (model in itemData) {
            section.add(ItemSchedule(model, context!!))
        }
    }
}

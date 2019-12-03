package com.mosl.mobile.schedule

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onSuccess
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentScheduleBinding
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ScheduleFragment : MOSLCommonFragment(), ScheduleContract {

    @Inject
    lateinit var presenter: SchedulePresenter

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var section: Section


    override fun getLayout(): Int {
        return R.layout.fragment_schedule
    }

    override fun getScheduleRequestInfo(): ScheduleRequestInfo {
        return ScheduleRequestInfo(
            ClientID = "4676",
            schedule = "2019-08-19",
            timestamp = "New"
        )
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)
        setupRecyclerView()

        presenter.start()
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

//        val itemData = listOf(
//            ScheduleRequestInfo(
//                "10:00 am - 11:00 am",
//                "Ms.Nandini Maheshwari,Business Development Head,Uber",
//                "Ms.Nandini Maheshwari",
//                "Venue- Ballroom Level"
//            ),
//            ScheduleRequestInfo(
//                "11:00 am - 12:00 pm",
//                "Mr.Punit Chhatwal,Ceo,Indian Hotels",
//                "Mr.Punit Chhatwal",
//                "Venue- Ballroom Level"
//            ),
//            ScheduleRequestInfo(
//                "12:00 pm - 01:00 pm",
//                "Mr.Harshwardhan Jala,Teenage Founder & Ceo,Aerobotics7",
//                "Mr.Harshwardhan Jala",
//                "Venue- Ballroom Level"
//            ),
//            ScheduleRequestInfo(
//                "01:00 pm - 02:00 pm",
//                "Mr.Prasun Basu - President-South Asia ,Nielsen",
//                "Mr.Prasun Basu",
//                "Venue- Ballroom Level"
//            ),
//            ScheduleRequestInfo(
//                "02:00 pm - 03:00 pm",
//                "Mr.C S Ghosh -Founder & Chairman, Bandhan Bank",
//                "Mr.C S Ghosh",
//                "Venue- Ballroom Level"
//            )
//        )
//        for (model in itemData) {
//            section.add(ItemSchedule(model, context!!))
//        }
    }

    override fun renderScheduleData(uiModel: UiModel<List<ScheduleResponseItemInfo>>) {

        uiModel.onSuccess {
            for (model in it) {
                section.add(ItemSchedule(model, context!!))
            }
        }
    }
}

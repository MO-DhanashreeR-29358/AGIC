package com.mosl.mobile.rm.corporateschedule


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.rm.R
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.rm.core.MOSLCommonFragment
import com.mosl.mobile.rm.corporatepager.CorporatePagerFragment
import com.mosl.mobile.rm.corporatepager.SchedulePagerAction
import com.mosl.mobile.rm.databinding.FragmentOverviewChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.checkTimeIsBetween
import com.mosl.groupiex.helper.groupie.GroupAdapter
import com.mosl.groupiex.helper.groupie.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CorporateScheduleFragment : MOSLCommonFragment(), CorporateChildContract {

    private val followUpUiEvent = PublishSubject.create<CorporateScheduleUiEvent>()

    @Inject
    lateinit var userManager: UserManager

    lateinit var binding: FragmentOverviewChildBinding

    @Inject
    lateinit var presenter: CorporateScheduleChildPresenter

    lateinit var section: DataManagerSection

    lateinit var publishSubject: Observable<SchedulePagerAction>

    override fun getLayout(): Int {
        return R.layout.fragment_overview_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewChildBinding.bind(view)
        setupRecyclerView()

        publishSubject =
            (parentFragment as? CorporatePagerFragment)?.tabPublishSubject ?: Observable.empty()

        presenter.start()
    }

    override fun onItemClicked(): Observable<CorporateScheduleUiEvent.OnMoreClick> {
        return followUpUiEvent.ofType()
    }

    override fun renderItemData(repList: List<CorporateScheduleResponseRepresentativeItemInfo>) {
        val dialogFragment = CorporateScheduleRepDialogFragment()
        dialogFragment.setData(repList)
        dialogFragment.show(childFragmentManager, "dialog")
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        /*binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }*/
    }


    override fun onTabClicked(): Observable<SchedulePagerAction.PagerClick> {
        return publishSubject.ofType()
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ): CorporateScheduleFragment {
            val fragment = CorporateScheduleFragment()

            return fragment
        }
    }

    override fun renderPage(
        uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>,
        shiftTime: String
    ) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            section.clearContent()

            if (shiftTime.equals("")) {
                val currentDate = AppDateFormats.df_Date6.format(Date())
                val dayArr = ""
                val separate1 = dayArr.split(",").toTypedArray()

                if (currentDate.equals(separate1[0]) && it[0].day.equals("day1", ignoreCase = true))
                    selection(it, true)
                else if (currentDate.equals(separate1[1]) && it[0].day.equals(
                        "day2",
                        ignoreCase = true
                    )
                )
                    selection(it, true)
                else if (currentDate.equals(separate1[2]) && it[0].day.equals(
                        "day3",
                        ignoreCase = true
                    )
                )
                    selection(it, true)
                else selection(it, false)
            } else {
                selectionSearch(it, shiftTime)
            }
        }
    }

    fun selection(it: List<CorporateScheduleResponseDayItemInfo>, coll: Boolean) {
        var currentTimeBetween = false
        var currentPosition = -1
        var expandPosition = -1

        for ((i, model) in it.withIndex()) {

            val startTime = model.shiftTiming
            val nextStartTime = model.shiftEndTime

            if (checkTimeIsBetween(startTime, nextStartTime) && !currentTimeBetween) {
                currentTimeBetween = true
                currentPosition = i
                expandPosition = i + 1
            }
            if (coll)
                section.add(
                    ItemCorporateSchedule(
                        model,
                        followUpUiEvent,
                        currentPosition,
                        requireContext(),
                        expandPosition
                    )
                )
            else
                section.add(ItemCorporateSchedule(model, followUpUiEvent, -1, requireContext(), -1))
        }
    }

    fun selectionSearch(
        it: List<CorporateScheduleResponseDayItemInfo>,
        shiftTime: String
    ) {
        var currentTimeBetween = false
        var currentPosition = -1
        var expandPosition = -1

        for ((i, model) in it.withIndex()) {
            val startTime = model.shiftTiming
            val serachShiftTiming = shiftTime
            if (startTime.equals(serachShiftTiming) && !currentTimeBetween
            ) {
                currentTimeBetween = true
                currentPosition = i
                expandPosition = i + 1
            }

            if (currentTimeBetween) {
//                binding.recyclerView.scrollToPosition(currentPosition)
                section.add(
                    ItemCorporateSchedule(
                        model,
                        followUpUiEvent,
                        -1,
                        requireContext(),
                        currentPosition
                    )
                )
            }
            else
                section.add(
                    ItemCorporateSchedule(model, followUpUiEvent, -1, requireContext(), -1)
                )
        }
    }

}

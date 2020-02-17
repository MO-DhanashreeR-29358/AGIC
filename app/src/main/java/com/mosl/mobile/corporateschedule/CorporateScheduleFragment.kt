package com.mosl.mobile.corporateschedule


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.corporatepager.CorporatePagerFragment
import com.mosl.mobile.corporatepager.SchedulePagerAction
import com.mosl.mobile.databinding.FragmentOverviewChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.checkTimeIsBetween
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
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


    fun newInstance(shiftTiming: String): CorporateScheduleFragment {
        val fragmentFirst = CorporateScheduleFragment()
        val args = Bundle()
        args.putString("shiftTiming", shiftTiming)
        fragmentFirst.setArguments(args)
        return fragmentFirst
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
        dialogFragment.setData(repList);
        dialogFragment.show(childFragmentManager, "dialog")
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
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

    override fun renderPage(uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            section.clearContent()

            val currentDate = AppDateFormats.df_Date6.format(Date())
//            val currentDate = "2019-08-02"
            val dayArr = userManager.user.day
            val separate1 = dayArr.split(",").toTypedArray()

            if (currentDate.equals(separate1[1]) && it[0].day.equals("day2"))
                selection(it, true)
            else if (!currentDate.equals(separate1[1]) && it[0].day.equals("day2"))
                selection(it, false)

            else if (currentDate.equals(separate1[2]) && it[0].day.equals("day3"))
                selection(it, true)
            else if (!currentDate.equals(separate1[2]) && it[0].day.equals("day3"))
                selection(it, false)

            else if (it[0].day.equals("day1") && !currentDate.equals(separate1[1]) && !currentDate.equals(separate1[2]))
                selection(it, true)
            else
                selection(it, false)
        }
    }

    fun selection(it: List<CorporateScheduleResponseDayItemInfo>, coll: Boolean) {
        val sortedList = it.sortedWith(compareBy({ it.shiftTiming }))
        var currentTimeBetween = false
        var currentPosition = -1

        for ((i, model) in sortedList.withIndex()) {

            if (i + 1 < sortedList.size) {

                val startTime = getTimeAMPM(model.shiftTiming)
                val nextStartTime = getTimeAMPM(sortedList[i + 1].shiftTiming)

                if (checkTimeIsBetween(startTime, nextStartTime) && !currentTimeBetween) {
                    currentTimeBetween = true
                    currentPosition = i
                }
            } else if (!currentTimeBetween) {
                currentPosition = i
                currentTimeBetween = true
            }

            if (coll)
                section.add(ItemCorporateSchedule(model, followUpUiEvent, currentPosition))
            else
                section.add(ItemCorporateSchedule(model, followUpUiEvent, -1))
        }
    }
}

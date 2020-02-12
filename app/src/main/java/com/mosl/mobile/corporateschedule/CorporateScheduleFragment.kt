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
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.corporatepager.CorporatePagerFragment
import com.mosl.mobile.databinding.FragmentOverviewChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.shared.util.checkTimeIsBefore
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CorporateScheduleFragment : MOSLCommonFragment(), CorporateChildContract {

    private val followUpUiEvent = PublishSubject.create<CorporateScheduleUiEvent>()


    lateinit var binding: FragmentOverviewChildBinding

    @Inject
    lateinit var presenter: CorporateScheduleChildPresenter

    lateinit var section: DataManagerSection

    lateinit var publishSubject: Observable<UiModel<List<CorporateScheduleResponseDayItemInfo>>>

    override fun getLayout(): Int {
        return R.layout.fragment_overview_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewChildBinding.bind(view)
        setupRecyclerView()

        publishSubject = (parentFragment as? CorporatePagerFragment)?.tabPublishSubject ?: Observable.empty()

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


    override fun onTabClicked(): Observable<UiModel<List<CorporateScheduleResponseDayItemInfo>>> {
        return publishSubject.ofType()
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ): OverviewChildFragment {
            val fragment = OverviewChildFragment()

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

            val sortedList = it.sortedWith(compareBy({ it.shiftTiming }))
            var currentTimeBetween = false
            var currentPosition = -1

            for ((i, model) in sortedList.withIndex()) {

                val startTime = getTimeAMPM(model.shiftTiming)

                if (checkTimeIsBefore(startTime) && !currentTimeBetween) {
                    currentTimeBetween = true
                    currentPosition = i
                }

                section.add(ItemCorporateSchedule(model, followUpUiEvent, currentPosition))
            }
        }
    }
}

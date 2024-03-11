package com.mosl.mobile.volunteer.panelpager


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.PanelData
import com.mosl.mobile.volunteer.core.MOSLCommonPager
import com.mosl.mobile.volunteer.databinding.FragmentPanelBinding
import com.mosl.mobile.volunteer.domain.panel.PanelDayResponse
import com.mosl.mobile.volunteer.domain.panel.PanelResponse
import com.mosl.mobile.volunteer.panel.PanelChildFragment
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelFragment : MOSLCommonPager(), PanelView {

    private lateinit var pagerAdapter: ViewPagerAdapter
    lateinit var binding: FragmentPanelBinding

    @Inject
    lateinit var presenter: PanelPresenter
    var panelDayWiseListData = mutableListOf<List<PanelDayResponse>>()

    val tabPublishSubject = PublishSubject.create<UiModel<List<PanelDayResponse>>>()

    @Inject
    @field:PanelData
    lateinit var panelData: StringPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_panel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelBinding.bind(view)
        setUpAdapter()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }

    override fun getItem(pos: Int): Fragment? {

        return when (pos) {
            0 -> {
                PanelChildFragment()
            }

            1 -> {
                PanelChildFragment()
            }

            2 -> {
                PanelChildFragment()
            }
            else -> null
        }
    }

    override fun getAdapter(): ViewPagerAdapter {
        return pagerAdapter
    }

    private fun setUpAdapter() {
        pagerAdapter = ViewPagerAdapter(childFragmentManager, requireContext(), this)
        pagerAdapter.setFragmentTitles(R.array.tabTitles)
        mPager.adapter = pagerAdapter

        mPager.addOnPageChangeListener(pageChangeListener)
        mIndicator.setupWithViewPager(mPager)


    }

    override fun onPageSelection(position: Int) {
        if (panelDayWiseListData.get(position).isNullOrEmpty()) {
            tabPublishSubject.onNext(
                UiModel.error(
                    ServiceFailureException(
                        NO_DATA_AVAILABLE_FOR_THE_DAY
                    )
                )
            )
        } else {
            tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.get(position)))
        }
        super.onPageSelection(position)
    }

    override fun renderPanelData(uiModel: UiModel<PanelResponse>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
            if (panelData.get().isEmpty()) {

                tabPublishSubject.onNext(UiModel.error(error))
            } else {
                val panelResponse: PanelResponse =
                    gson.fromJson(panelData.get(), PanelResponse::class.java)
                panelResponse.responseData()
            }
        }

        uiModel.onSuccess {
            val json = gson.toJson(it)
            panelData.set(json)
            it.responseData()

        }
    }

    private fun PanelResponse.responseData() {
        panelDayWiseListData = PanelDayWiseListData() as MutableList<List<PanelDayResponse>>
        val dayPanel = PanelDayWiseListData().get(0)
        if (dayPanel.isNotEmpty()) {
            tabPublishSubject.onNext(UiModel.success(dayPanel))
        } else {
            tabPublishSubject.onNext(
                UiModel.error(
                    ServiceFailureException(
                        NO_DATA_AVAILABLE_FOR_THE_DAY
                    )
                )
            )
        }
    }

    companion object {
        const val PANEL_DAY_LIST = "panel_day_list"
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
    }

}

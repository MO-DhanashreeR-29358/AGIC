package com.mosl.mobile.panelpager


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentPanelBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.domain.panel.PanelResponse
import com.mosl.mobile.panel.PanelChildFragment
import timber.log.Timber
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelFragment : MOSLCommonPager(), PanelView {

    private lateinit var pagerAdapter: ViewPagerAdapter
    lateinit var binding: FragmentPanelBinding

    @Inject
    lateinit var presenter: PanelPresenter
    var panelDayWiseListData= mutableListOf<List<PanelDayResponse>>()
    override fun getLayout(): Int {
        return R.layout.fragment_panel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelBinding.bind(view)
        setUpAdapter()
        presenter.start()
    }

    override fun getItem(pos: Int): Fragment? {

        val args = Bundle().apply {
            if(panelDayWiseListData.isNotEmpty()){
                putParcelableArrayList(PANEL_DAY_LIST,
                    panelDayWiseListData.get(pos).getParcelablePanelDayList() as ArrayList<PanelListDayItemData>)
            }

        }
        return when (pos) {
            0 -> {
            val panelFragment =PanelChildFragment()
                panelFragment.arguments =args
                panelFragment
            }
            1 -> {
                val panelFragment =PanelChildFragment()
                panelFragment.arguments =args
                panelFragment
            }
            2 -> {
                val panelFragment =PanelChildFragment()
                panelFragment.arguments =args
                panelFragment
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


    private fun List<PanelDayResponse>.getParcelablePanelDayList(): List<PanelListDayItemData> {
        return map {
            PanelListDayItemData(
               day=it.day,
                cRepresentiveName = it.cRepresentiveName,
                designation = it.designation,
                representativeStarttime = it.representativeStarttime,
                representativeEndtime = it.representativeEndtime,
                panelImagePath = it.panelImagePath,
                speakerIntroduction = it.speakerIntroduction

            )
        }
    }

    override fun onPageSelection(position: Int) {
        Timber.d("Page Selected---> %d", position)
        super.onPageSelection(position)
    }

    override fun renderPanelData(uiModel: UiModel<PanelResponse>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
           showOnFailurePopup(error)
        }

        uiModel.onSuccess {
            panelDayWiseListData= it.PanelDayWiseListData() as MutableList<List<PanelDayResponse>>
        }
    }
    companion object {
        const val  PANEL_DAY_LIST = "panel_day_list"
    }

}

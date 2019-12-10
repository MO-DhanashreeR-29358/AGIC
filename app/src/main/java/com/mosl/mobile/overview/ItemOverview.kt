package com.mosl.mobile.overview

import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewChildBinding
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemOverview(
    overviewData: OverviewPagerResponseDayItemInfo,
    private val publishSubject: PublishSubject<OverviewUiEvent>
) :
    BindableItem<ItemFragmentOverviewChildBinding>(), View.OnClickListener {
    private val itemData = overviewData


    override fun bind(viewBinding: ItemFragmentOverviewChildBinding, position: Int) {

        viewBinding.apply {
            tvMore.setOnClickListener(this@ItemOverview)

            if (itemData.Rep.size > 1) {
                tvMore.visibility = View.VISIBLE
                val count = itemData.Rep.size - 1
                tvMore.text = String.format("...%d More", count)
            }

            tvOverviewDesignation.text = itemData.corporate_Name
            tvOverviewName.text = itemData.Rep[0].NAME
            overviewTvTimeFrom.text = getTimeAMPM(itemData.start_time)
            tvOverviewTimeTo.text = getTimeAMPM(itemData.end_time)

            iconOverviewMctrack.apply {
                when (itemData.Nature_of_meeting) {
                    "1-1" -> setImageResource(R.drawable.ic_1x1_small)
                    "2-1" -> setImageResource(R.drawable.ic_2x1_small)
                    "3-1" -> setImageResource(R.drawable.ic_3x1_small)
                    "4-1" -> setImageResource(R.drawable.ic_4x1_small)
                    "Small Group" -> setImageResource(R.drawable.ic_group_small)
                    "Workshop" -> setImageResource(R.drawable.ic_mtrack_small)
                    else -> setImageResource(R.drawable.ic_mtrack_small)
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_overview_child
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_more -> publishSubject.onNext(OverviewUiEvent.OnMoreClick(itemData.Rep))
        }
    }
}

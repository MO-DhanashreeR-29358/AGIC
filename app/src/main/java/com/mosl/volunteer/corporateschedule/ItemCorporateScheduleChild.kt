package com.mosl.volunteer.corporateschedule

import android.view.View
import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.ItemFragmentCorporateScheduleSubchildBinding
import com.mosl.volunteer.domain.corporateschedulepager.CorporateScheduleResponseDayAttendanceItemInfo
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemCorporateScheduleChild(
    scheduleData: CorporateScheduleResponseDayAttendanceItemInfo,
    private val publishSubject: PublishSubject<CorporateScheduleUiEvent>
) :
    BindableItem<ItemFragmentCorporateScheduleSubchildBinding>(), View.OnClickListener {

    private val itemData = scheduleData

    override fun bind(viewBinding: ItemFragmentCorporateScheduleSubchildBinding, position: Int) {

        viewBinding.apply {

            tvFundName.text = itemData.FundName

            if (itemData.representative.size == 2) {
                tvRepresentative1.text =
                    String.format(
                        "%s, %s",
                        itemData.representative[0].name,
                        itemData.representative[0].designation
                    )
                tvRepresentative2.text =
                    String.format(
                        "%s, %s",
                        itemData.representative[1].name,
                        itemData.representative[1].designation
                    )
                tvMore.visibility = View.GONE
            } else if (itemData.representative.size == 1) {
                tvRepresentative1.text =
                    String.format(
                        "%s, %s",
                        itemData.representative[0].name,
                        itemData.representative[0].designation
                    )
                tvRepresentative2.visibility = View.GONE
                tvMore.visibility = View.GONE
            } else if (itemData.representative.size == 0) {
                tvRepresentative1.visibility = View.GONE
                tvRepresentative2.visibility = View.GONE
                tvMore.visibility = View.GONE
            } else if (itemData.representative.size > 2) {
                tvRepresentative1.text =
                    String.format(
                        "%s, %s",
                        itemData.representative[0].name,
                        itemData.representative[0].designation
                    )
                tvRepresentative2.text =
                    String.format(
                        "%s, %s",
                        itemData.representative[1].name,
                        itemData.representative[1].designation
                    )
                tvMore.visibility = View.VISIBLE

                tvMore.text = String.format("...%d More", (itemData.representative.size - 2))
                tvMore.setOnClickListener(this@ItemCorporateScheduleChild)
            }
        }
    }

    @JvmField
    protected val onRetryClick = View.OnClickListener {

    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_corporate_schedule_subchild
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_more -> publishSubject.onNext(
                CorporateScheduleUiEvent.OnMoreClick(
                    itemData.representative
                )
            )
        }
    }
}
package com.mosl.mobile.corporatepager

import android.view.View
import com.mosl.agicapp.R
import com.mosl.agicapp.databinding.ItemSearchCorporateScheduleBinding
import com.mosl.mobile.domain.corporateschedulepager.SearchCorporateScheduleItemInfo
import com.mosl.groupiex.helper.databind.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemSearchCorporateSchedule(
    scheduleData: SearchCorporateScheduleItemInfo,
    private val publishSubject: PublishSubject<CorporatePagerUiEvent>
) :
    BindableItem<ItemSearchCorporateScheduleBinding>(), View.OnClickListener {

    private val itemData = scheduleData


    override fun bind(viewBinding: ItemSearchCorporateScheduleBinding, position: Int) {

        viewBinding.apply {

            tvFundName.text = itemData.represntiveName
            tvFundName.setOnClickListener(this@ItemSearchCorporateSchedule)
        }
    }

    @JvmField
    protected val onRetryClick = View.OnClickListener {

    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_FundName -> publishSubject.onNext(
                CorporatePagerUiEvent.OnSearchClick(itemData)
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_search_corporate_schedule
    }
}
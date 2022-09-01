package com.mosl.mobile.volunteer.attendance.corporateqr

import android.os.Bundle
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.appuser.model.CorporateModel
import com.mosl.mobile.volunteer.constants.NavigationConstants
import com.mosl.mobile.volunteer.databinding.ItemAttendanceCorporateNameBinding
import com.mosl.mobile.volunteer.menu.ScreenNavigator
import com.xwray.groupie.databinding.BindableItem

class ItemCorporateName(
    private val model: CorporateModel,
    private val navigator: ScreenNavigator
): BindableItem<ItemAttendanceCorporateNameBinding>() {

    override fun bind(viewBinding: ItemAttendanceCorporateNameBinding, position: Int) {
        viewBinding.tvCorporateName.text = model.corporateName
        viewBinding.tvCorporateName.setOnClickListener {
            navigator.openPage(
                pageId = NavigationConstants.NAVIGATE_TO_QR_CODE_SCAN_FRAGMENT,
                args = Bundle().apply {
                    putString(QrCodeScanContract.EXTRA_COMPANY_ID, model.companyId)
                }
            )
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_attendance_corporate_name
    }

    override fun getId(): Long {
        return model.hashCode().toLong()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as ItemCorporateName?

        return model == that!!.model
    }

    override fun hashCode(): Int {
        return model.hashCode()
    }
}
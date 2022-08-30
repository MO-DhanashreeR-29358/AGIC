package com.mosl.mobile.volunteer.corporateqr

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemAttendanceCorporateNameBinding
import com.xwray.groupie.databinding.BindableItem

class ItemCorporateName(val corporateName: String): BindableItem<ItemAttendanceCorporateNameBinding>() {

    override fun bind(viewBinding: ItemAttendanceCorporateNameBinding, position: Int) {
        viewBinding.tvCorporateName.text = corporateName
    }

    override fun getLayout(): Int {
        return R.layout.item_attendance_corporate_name
    }

    override fun getId(): Long {
        return corporateName.hashCode().toLong()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false

        val that = obj as ItemCorporateName?

        return corporateName == that!!.corporateName
    }

    override fun hashCode(): Int {
        return corporateName.hashCode()
    }
}
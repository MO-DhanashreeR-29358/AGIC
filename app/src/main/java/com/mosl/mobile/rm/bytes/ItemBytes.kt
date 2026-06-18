package com.mosl.mobile.rm.bytes

import com.mosl.mobile.rm.R
import com.mosl.mobile.rm.databinding.ItemBytesBinding
import com.mosl.mobile.domain.bytes.BytesItemModel
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_4
import com.mosl.shared.AppDateFormats.DF_TIME_hh
import com.mosl.shared.util.getFormatedDate
import com.mosl.groupiex.helper.databind.BindableItem

class ItemBytes(private val data: BytesItemModel) : BindableItem<ItemBytesBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_bytes
    }

    override fun bind(viewBinding: ItemBytesBinding, position: Int) {
        viewBinding.tvBytesTime.text = getFormatedDate(data.entryDate,DF_JSON_DATE_4,DF_TIME_hh)
        viewBinding.tvBytesDesc.text = data.byte
    }

    override fun getId(): Long {
        return data.byte.hashCode().toLong()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false

        val that = obj as ItemBytes?

        return data == that!!.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}
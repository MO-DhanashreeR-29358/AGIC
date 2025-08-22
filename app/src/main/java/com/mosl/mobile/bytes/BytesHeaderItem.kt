package com.mosl.mobile.bytes

import com.mosl.agicapp.R
import com.mosl.agicapp.databinding.ItemBytesHeaderBinding
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_10
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_11
import com.mosl.shared.util.getFormatedDate
import com.mosl.groupiex.helper.databind.BindableItem

class BytesHeaderItem(val title: String) : BindableItem<ItemBytesHeaderBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_bytes_header
    }

    override fun bind(viewBinding: ItemBytesHeaderBinding, position: Int) {
        viewBinding.txtSectionTitle.text = getFormatedDate(title, DF_JSON_DATE_10,DF_JSON_DATE_11)
    }

    override fun getId(): Long {
        return title.hashCode().toLong()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        val that = obj as BytesHeaderItem
        return title == that.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
package com.mosl.mobile.volunteer.notification

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemBytesHeaderBinding
import com.mosl.groupiex.helper.databind.BindableItem

class ItemNotificationHeaderDate(val title: String) : BindableItem<ItemBytesHeaderBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_bytes_header
    }

    override fun bind(viewBinding: ItemBytesHeaderBinding, position: Int) {
        viewBinding.txtSectionTitle.text = title
    }

    override fun getId(): Long {
        return title.hashCode().toLong()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        val that = obj as ItemNotificationHeaderDate
        return title == that.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
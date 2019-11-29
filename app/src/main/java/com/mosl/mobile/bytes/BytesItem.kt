package com.mosl.mobile.bytes

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemBytesBinding
import com.xwray.groupie.databinding.BindableItem

class BytesItem(private val bytesData: BytesData) : BindableItem<ItemBytesBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_bytes
    }

    override fun bind(viewBinding: ItemBytesBinding, position: Int) {
        viewBinding.tvBytesTime.text = bytesData.time
        viewBinding.tvBytesDesc.text = bytesData.desc
    }

    override fun getId(): Long {
        return bytesData.desc.hashCode().toLong()
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false

        val that = obj as BytesItem?

        return bytesData == that!!.bytesData
    }

    override fun hashCode(): Int {
        return bytesData.hashCode()
    }
}
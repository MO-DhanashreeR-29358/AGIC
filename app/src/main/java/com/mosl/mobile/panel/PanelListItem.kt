package com.mosl.mobile.panel

import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentPanelBinding
import com.xwray.groupie.databinding.BindableItem


class PanelListItem(panelItemData: PanelDataHeader) : BindableItem<ItemFragmentPanelBinding>() {
    private val itemData = panelItemData
    private var isShowViews: Boolean = false

    override fun bind(viewBinding: ItemFragmentPanelBinding, position: Int) {

        viewBinding.txtName.text = itemData.name
        viewBinding.txtTime.text = itemData.time
        viewBinding.txtDesignation.text = itemData.designation
        viewBinding.txtDesc.text = itemData.desc

        viewBinding.iconMore.setOnClickListener {
            if (isShowViews) {
                isShowViews = false
                btnMoreClick(viewBinding, isShowViews)
            } else {
                isShowViews = true
                btnMoreClick(viewBinding, isShowViews)
            }
        }

    }

    private fun btnMoreClick(viewBinding: ItemFragmentPanelBinding, isMoreButtonClick: Boolean) {
        if (isMoreButtonClick) {
            viewBinding.txtDesc.visibility = View.VISIBLE
            viewBinding.view.visibility = View.VISIBLE
        } else {
            viewBinding.txtDesc.visibility = View.GONE
            viewBinding.view.visibility = View.GONE
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_panel
    }
}

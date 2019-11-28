package com.mosl.mobile.panel

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentPanelBinding
import com.xwray.groupie.databinding.BindableItem


class PanelListItem(val panelItemData: PanelDataHeader, val context: Context) :
    BindableItem<ItemFragmentPanelBinding>() {

    private var isShowViews: Boolean = false

    override fun bind(viewBinding: ItemFragmentPanelBinding, position: Int) {

        viewBinding.txtName.text = panelItemData.name
        viewBinding.txtTime.text = panelItemData.time
        viewBinding.txtDesignation.text = panelItemData.designation
        viewBinding.txtDesc.text = panelItemData.desc

        Glide.with(context)
            .load("")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.agic_15)
            .into(viewBinding.imgProfileIcon)


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

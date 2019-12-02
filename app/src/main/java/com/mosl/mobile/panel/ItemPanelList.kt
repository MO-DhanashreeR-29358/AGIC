package com.mosl.mobile.panel

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentPanelChildBinding
import com.xwray.groupie.databinding.BindableItem


class ItemPanelList(private val panelItemData: PanelChildData, val context: Context) :
    BindableItem<ItemFragmentPanelChildBinding>() {

    private var isShowViews: Boolean = false

    override fun bind(viewBinding: ItemFragmentPanelChildBinding, position: Int) {

        viewBinding.tvPanelName.text = panelItemData.name
        viewBinding.tvPanelTime.text = panelItemData.time
        viewBinding.tvPanelDesignation.text = panelItemData.designation
        viewBinding.tvPanelDesc.text = panelItemData.desc

        Glide.with(context)
            .load("")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.agic_15)
            .into(viewBinding.imgPanelProfile)


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

    private fun btnMoreClick(
        viewBinding: ItemFragmentPanelChildBinding,
        isMoreButtonClick: Boolean
    ) {
        if (isMoreButtonClick) {

            viewBinding.tvPanelDesc.visibility = View.VISIBLE

            viewBinding.view.visibility = View.VISIBLE
        } else {
            viewBinding.tvPanelDesc.visibility = View.GONE
            viewBinding.view.visibility = View.GONE
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_panel_child
    }
}

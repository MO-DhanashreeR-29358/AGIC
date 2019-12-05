package com.mosl.mobile.panel

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentPanelChildBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.panelpager.PanelListDayItemData
import com.mosl.shared.util.getTime
import com.xwray.groupie.databinding.BindableItem


class ItemPanelList(private val data: PanelDayResponse, val context: Context) :
    BindableItem<ItemFragmentPanelChildBinding>() {

    private var isShowViews: Boolean = false

    override fun bind(viewBinding: ItemFragmentPanelChildBinding, position: Int) {

        viewBinding.tvPanelName.text = data.cRepresentiveName
        viewBinding.tvPanelTime.text =getTime(data.representativeStarttime,data.representativeEndtime)
        viewBinding.tvPanelDesignation.text = data.designation
        viewBinding.tvPanelIntro.text = data.speakerIntroduction

        Glide.with(context)
            .load(data.panelImagePath)
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

            viewBinding.tvPanelIntro.visibility = View.VISIBLE

            viewBinding.view.visibility = View.VISIBLE
        } else {
            viewBinding.tvPanelIntro.visibility = View.GONE
            viewBinding.view.visibility = View.GONE
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_panel_child
    }
}

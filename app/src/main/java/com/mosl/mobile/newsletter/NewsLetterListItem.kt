package com.mosl.mobile.newsletter

import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNewsLetterBinding
import com.xwray.groupie.databinding.BindableItem


class NewsLetterListItem(panelItemData: NewsLetterHeader) : BindableItem<ItemNewsLetterBinding>() {
    private val itemData = panelItemData
    private var isShowViews: Boolean = false

    override fun bind(viewBinding: ItemNewsLetterBinding, position: Int) {

        viewBinding.txtName.text = itemData.url
        viewBinding.txtDesc.text = itemData.description

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

    private fun btnMoreClick(viewBinding: ItemNewsLetterBinding, isMoreButtonClick: Boolean) {
        if (isMoreButtonClick) {
            viewBinding.txtDesc.visibility = View.VISIBLE
            viewBinding.view.visibility = View.VISIBLE
        } else {
            viewBinding.txtDesc.visibility = View.GONE
            viewBinding.view.visibility = View.GONE
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_news_letter
    }
}

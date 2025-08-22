package com.mosl.mobile.panel

import android.content.Context
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosl.agicapp.R
import com.mosl.agicapp.databinding.ItemFragmentPanelChildBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.shared.util.getTime
import com.mosl.groupiex.helper.databind.BindableItem
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


class ItemPanelList(
    private val data: PanelDayResponse, val context: Context,
    val publishSubject: PublishSubject<PanelUiEvent>
) :
    BindableItem<ItemFragmentPanelChildBinding>(), View.OnClickListener {

    private var isShowViews: Boolean = false

    lateinit var viewBinding: ItemFragmentPanelChildBinding

    override fun bind(viewBinding: ItemFragmentPanelChildBinding, position: Int) {
        this.viewBinding = viewBinding
        viewBinding.apply {
            tvPanelName.text = data.cRepresentiveName
            tvPanelTime.text =
                getTime(data.representativeStarttime, data.representativeEndtime)
            tvPanelDesignation.text = data.designation
            tvPanelIntro.text = data.speakerIntroduction

            Log.d("Panel",data.panelImagePath)
            Timber.i("Panel ${data.panelImagePath}")

            val imageUrl = "https://galaxy.motilaloswal.com/Conference/"
            val apiImagePath = data.panelImagePath.replace(" ", "%20")

            Glide.with(context)
                .load(imageUrl+apiImagePath)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.agic_15)
                .into(imgPanelProfile)


            tvPanelDesignation.setOnClickListener(this@ItemPanelList)
            iconMore.setOnClickListener(this@ItemPanelList)
            tvPanelName.setOnClickListener(this@ItemPanelList)
            tvPanelTime.setOnClickListener(this@ItemPanelList)
        }
    }

    private fun btnMoreClick(
        isMoreButtonClick: Boolean
    ) {
        viewBinding.apply {
            if (isMoreButtonClick) {
                viewBinding.tvPanelIntro.visibility = View.VISIBLE
                viewBinding.view.visibility = View.VISIBLE
            } else {
                viewBinding.tvPanelIntro.visibility = View.GONE
                viewBinding.view.visibility = View.GONE
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_panel_child
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_panel_name -> publishSubject.onNext(
                PanelUiEvent.ItemClick(data)
            )

            v?.id == R.id.tv_panel_time -> publishSubject.onNext(
                PanelUiEvent.ItemClick(data)
            )

            v?.id == R.id.icon_more -> {
                if (isShowViews) {
                    isShowViews = false
                    btnMoreClick(isShowViews)
                } else {
                    isShowViews = true
                    btnMoreClick(isShowViews)
                }
            }

            v?.id == R.id.tv_panel_designation -> {
                if (isShowViews) {
                    isShowViews = false
                    btnMoreClick(isShowViews)
                } else {
                    isShowViews = true
                    btnMoreClick(isShowViews)
                }
            }
        }
    }
}

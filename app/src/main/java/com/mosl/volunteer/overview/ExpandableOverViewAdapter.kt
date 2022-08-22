package com.mosl.volunteer.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.databinding.DataBindingUtil
import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.*
import com.mosl.volunteer.domain.overviewpager.RmOverViewMeetingResponseInfo
import com.mosl.volunteer.domain.overviewpager.RmOverviewResponseDayItemInfo
import io.reactivex.subjects.PublishSubject

class ExpandableOverViewAdapter(private val publishSubject: PublishSubject<OverviewUiEvent>,
   val data: List<RmOverViewMeetingResponseInfo>
) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return data[groupPosition].meetingList[childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {

        val childText = getChild(groupPosition, childPosition) as RmOverviewResponseDayItemInfo

        var binding: ItemRmDaywiseDataChildBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rm_daywise_data_child, parent, false
        );


        binding.tvFundName.text = childText.fundName
        binding.tvRoomNumber.text = childText.roomNo
        binding.tvName.text = childText.companyName
        binding.tvClientsName.text = childText.clientName
        binding.tvClientName.text = childText.rep[0].name
        if (childText.rep.size > 1) {
            binding.tvMore.visibility = View.VISIBLE
            val count = childText.rep.size - 1
            binding.tvMore.text = String.format("...%d More", count)
        }

        binding.tvMore.setOnClickListener {
            publishSubject.onNext(OverviewUiEvent.OnMoreClick(childText.rep))
        }
        binding.iconRmGroup.apply {
            when (childText.natureOfMeeting) {
                "1-1" -> setImageResource(R.drawable.ic_1x1_small)
                "2-1" -> setImageResource(R.drawable.ic_2x1_small)
                "3-1" -> setImageResource(R.drawable.ic_3x1_small)
                "4-1" -> setImageResource(R.drawable.ic_4x1_small)
                "Small Group" -> setImageResource(R.drawable.ic_group_small)
                "Workshop" -> setImageResource(R.drawable.ic_mtrack_small)
                else -> setImageResource(R.drawable.ic_mtrack_small)
            }
        }

        return binding.root
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.data[groupPosition].meetingList.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.data[groupPosition].meetingTime
    }

    override fun getGroupCount(): Int {
        return this.data.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {

        val headerTitle = getGroup(groupPosition) as String

        var binding: ItemRmDataParentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rm_data_parent, parent, false
        );

//        Glide.with(parent.getContext())
//            .load(data[groupPosition].IMAGEPATH.toString())
//            .into(binding.imgProfileIcon);


//        binding.iconMore.setImageResource(R.drawable.ic_more_horiz);



        binding.tvHeaderName.text = headerTitle

        return binding.root
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
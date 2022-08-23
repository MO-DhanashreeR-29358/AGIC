package com.mosl.mobile.volunteer.rmclientwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.*
import com.mosl.mobile.volunteer.domain.overviewpager.*
import com.mosl.mobile.volunteer.overview.OverviewUiEvent
import io.reactivex.subjects.PublishSubject

class ExpandableClientViewAdapter(
    private val publishSubject: PublishSubject<OverviewUiEvent>,
   val data: List<RmClientWiseResponseInfo>
) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return data[groupPosition].FundRepresentative[childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {

        val childText = getChild(groupPosition, childPosition) as FundRepresentativeItemInfo

        var binding: ItemRmClientWiseDataChildBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rm_client_wise_data_child, parent, false
        )


        binding.tvFundRepresentativeName.text = childText.fundRepresentativeName
        var meetingTimeStringBuffer : StringBuffer = StringBuffer()
        for (i in childText.meetingTimeDisplay.indices){
            var meetingListData : ClientWiseMeetingSlotItemInfo = childText.meetingTimeDisplay[i]
            meetingTimeStringBuffer.append(meetingListData.meetingSlotTime+"\n")
        }
        val groupAdapter = ItemOverviewExpandable(publishSubject,childText.meetingTimeDisplay)
        binding.rvClientMeetingList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = groupAdapter
        }

        /*binding.tvRoomNumber.text = childText.meetingTimeDisplay
        binding.tvName.text = childText.companyName
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
        }*/

        return binding.root
    }


    override fun getChildrenCount(groupPosition: Int): Int {
        return this.data[groupPosition].FundRepresentative.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.data[groupPosition].FundName
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
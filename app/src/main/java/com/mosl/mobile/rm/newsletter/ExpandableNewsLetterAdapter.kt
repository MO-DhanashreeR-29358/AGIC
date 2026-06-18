package com.mosl.mobile.rm.newsletter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.mosl.mobile.rm.R
import com.mosl.mobile.rm.databinding.ItemNewsLetterChildBinding
import com.mosl.mobile.rm.databinding.ItemNewsLetterParentBinding
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo

class ExpandableNewsLetterAdapter(
   val data: List<NewsLetterResponseItemInfo>
) : BaseExpandableListAdapter() {





    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return data[groupPosition].HeadLines.toString()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        lateinit var binding: ItemNewsLetterChildBinding

        val childText = getChild(groupPosition, childPosition) as String

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_news_letter_child, parent, false
        );


        binding.txtChildDescription.text = childText

        return binding.root
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.data[groupPosition].Title.toString()
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
        lateinit var binding: ItemNewsLetterParentBinding

        val headerTitle = getGroup(groupPosition) as String

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_news_letter_parent, parent, false
        );

        Glide.with(parent.getContext())
            .load(data[groupPosition].IMAGEPATH.toString())
            .into(binding.imgProfileIcon);


        binding.iconMore.setImageResource(R.drawable.ic_more_horiz);



        binding.txtNewsletterParentTitle.text = headerTitle

        return binding.root
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
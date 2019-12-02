package com.mosl.mobile.newsletter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.databinding.DataBindingUtil
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNewsLetterChildBinding
import com.mosl.mobile.databinding.ItemNewsLetterParentBinding
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo

class ExpandableNewsLetterAdapter(
    val _context: Context,
    var _listDataHeader: List<NewsLetterResponseItemInfo>?
    // child data in format of header Title, child Title
) : BaseExpandableListAdapter() {


    var _listDataHeaderFiltered: List<NewsLetterResponseItemInfo>
    var _listDataHeaderOriginal = ArrayList<NewsLetterResponseItemInfo>()

    init {
        _listDataHeaderFiltered = _listDataHeader!!
        _listDataHeaderOriginal.addAll(_listDataHeader!!)
    }

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return _listDataHeaderFiltered[groupPosition].HeadLines.toString()
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


        binding.txtNewsletterChildDescription.text = childText

        return binding.root
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return this._listDataHeaderFiltered[groupPosition].Title.toString()
    }

    override fun getGroupCount(): Int {
        return this._listDataHeaderFiltered.size
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


        if (isExpanded) {
            binding.imgProfileIcon.setImageResource(R.mipmap.ic_launcher_round);
            binding.iconMore.setImageResource(R.drawable.ic_more_horiz);
        } else {
            binding.imgProfileIcon.setImageResource(R.mipmap.ic_launcher_round);
            binding.iconMore.setImageResource(R.drawable.ic_more_horiz);
        }

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
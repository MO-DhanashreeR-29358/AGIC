package com.mosl.mobile.newsletter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.mosl.mobile.R
import kotlinx.android.synthetic.main.item_faqs_child.view.*
import kotlinx.android.synthetic.main.item_faqs_parent.view.*

class ExpandableFaqsListAdapter(
    val _context: Context,
    var _listDataHeader: ArrayList<NewsLetterHeader>?,
    private val _listChild: List<NewsLetterHeader>? // header titles
    // child data in format of header title, child title
) : BaseExpandableListAdapter() {


    var _listDataHeaderFiltered: ArrayList<NewsLetterHeader>
    var _listDataHeaderOriginal = ArrayList<NewsLetterHeader>()

    init {
        _listDataHeaderFiltered = _listDataHeader!!
        _listDataHeaderOriginal.addAll(_listDataHeader!!)
    }

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return _listDataHeaderFiltered[groupPosition].description
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        var convertView = convertView

        val childText = getChild(groupPosition, childPosition) as String

        if (convertView == null) {
            val infalInflater = this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.item_faqs_child, null)
        }

        convertView!!.txtFaqAnswer.text = childText

        /*val txtListChild = convertView!!
                .findViewById(R.id.lblListItem) as TextView
        txtListChild.text = childText
        */
        return convertView!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return this._listDataHeaderFiltered[groupPosition].url
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
        var convertView = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val infalInflater = this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.item_faqs_parent, null)
        }

        if (isExpanded) {
            convertView!!.imgProfileIcon.setImageResource(R.mipmap.ic_launcher_round);
            convertView!!.icon_more.setImageResource(R.drawable.ic_more_horiz);
        } else {
            convertView!!.imgProfileIcon.setImageResource(R.mipmap.ic_launcher_round);
            convertView!!.icon_more.setImageResource(R.drawable.ic_more_horiz);
        }

        convertView?.setBackgroundResource(R.color.white)


        convertView!!.txtFaqs.text = headerTitle

        return convertView!!
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
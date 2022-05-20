package com.mosl.mobile.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mosl.mobile.R
import com.mosl.mobile.domain.overviewpager.OverviewResponseDayItemInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo

class ItemOverviewExpandable(val list: MutableList<OverviewResponseDayItemInfo>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType== 1){
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rm_data_parent, parent,false)
            GroupViewHolder(rowView)
        } else {
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rm_data_child, parent,false)
            ChildViewHolder(rowView)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val dataList = list[position]
        if (dataList.viewType == 1) {
            holder as GroupViewHolder
            holder.apply {
                parentTV?.text = dataList.corporateName
                downIV?.setOnClickListener {
                    expandOrCollapseParentItem(dataList,position)
                }
            }
        } else {
            holder as ChildViewHolder

            holder.apply {
                val singleService = dataList.rep.first()
                childTV?.text =singleService.name
            }
        }
    }
    private fun expandOrCollapseParentItem(singleBoarding: OverviewResponseDayItemInfo,position: Int) {

        if (singleBoarding.isExpanded) {
            collapseParentRow(position)
        } else {
            expandParentRow(position,singleBoarding)
        }
    }

    private fun expandParentRow(position: Int,parentModel: OverviewResponseDayItemInfo){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.rep
        currentBoardingRow.isExpanded = true
        var nextPosition = position
        if(currentBoardingRow.viewType==1){

            services.forEach { service ->
                parentModel.viewType = 0
                val subList : ArrayList<OverviewResponseRepresntativeInfo> = ArrayList()
                subList.add(service)
                parentModel.rep=subList
                list.add(++nextPosition,parentModel)
            }
            notifyDataSetChanged()
        }
    }

    private fun collapseParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.rep
        list[position].isExpanded = false
        if(list[position].viewType==1){
            services.forEach { _ ->
                list.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].viewType

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class GroupViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val parentTV = row.findViewById(R.id.tv_header_name) as TextView?
        val downIV  = row.findViewById(R.id.icon_rm_header) as ImageView?
    }
    class ChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val childTV = row.findViewById(R.id.tv_fund_name) as TextView?

    }
}
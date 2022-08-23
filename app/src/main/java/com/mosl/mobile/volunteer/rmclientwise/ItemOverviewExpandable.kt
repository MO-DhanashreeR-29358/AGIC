package com.mosl.mobile.volunteer.rmclientwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.domain.overviewpager.ClientWiseMeetingSlotItemInfo
import com.mosl.mobile.volunteer.overview.OverviewUiEvent
import io.reactivex.subjects.PublishSubject

class ItemOverviewExpandable(private val publishSubject: PublishSubject<OverviewUiEvent>
                                 ,val list: List<ClientWiseMeetingSlotItemInfo>)
    : RecyclerView.Adapter<ItemOverviewExpandable.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rm_data_child, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = list[position]

        holder.apply {

            textViewMeetingTime.text = ItemsViewModel.meetingSlotTime
            textViewRoomNo.text = ItemsViewModel.meeting[0].roomNo
            textViewName.text = ItemsViewModel.meeting[0].corporateName
            textViewClientName.text = ItemsViewModel.meeting[0].rep[0].name

            if (ItemsViewModel.meeting[0].rep.size > 1) {
                textViewMore.visibility = View.VISIBLE
                val count = ItemsViewModel.meeting[0].rep.size - 1
                textViewMore.text = String.format("...%d More", count)
            }

            textViewMore.setOnClickListener {
                publishSubject.onNext(OverviewUiEvent.OnMoreClick(ItemsViewModel.meeting[0].rep))
            }

            imageView.apply {
                when (ItemsViewModel.meeting[0].natureOfMeeting) {
                    "1-1" -> setImageResource(R.drawable.ic_1x1_small)
                    "2-1" -> setImageResource(R.drawable.ic_2x1_small)
                    "3-1" -> setImageResource(R.drawable.ic_3x1_small)
                    "4-1" -> setImageResource(R.drawable.ic_4x1_small)
                    "Small Group" -> setImageResource(R.drawable.ic_group_small)
                    "Workshop" -> setImageResource(R.drawable.ic_mtrack_small)
                    else -> setImageResource(R.drawable.ic_mtrack_small)
                }
            }
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon_rm_group)
        val textViewMeetingTime: TextView = itemView.findViewById(R.id.tv_fund_name)
        val textViewRoomNo: TextView = itemView.findViewById(R.id.tv_roomNumber)
        val textViewName: TextView = itemView.findViewById(R.id.tv_name)
        val textViewClientName: TextView = itemView.findViewById(R.id.tv_client_name)
        val textViewMore: TextView = itemView.findViewById(R.id.tv_more)
    }

}
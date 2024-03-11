package com.mosl.mobile.volunteer.contactrm

import android.view.View
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.appuser.model.ContactListItemData
import com.mosl.mobile.volunteer.databinding.ItemFragmentContactBinding
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ContactListitem(
    private val data: ContactListItemData,
    private val publishSubject: PublishSubject<CallUiEvent>
) :
    BindableItem<ItemFragmentContactBinding>(), View.OnClickListener {

    override fun getLayout(): Int {
        return R.layout.item_fragment_contact

    }

    override fun bind(viewBinding: ItemFragmentContactBinding, position: Int) {

        viewBinding.tvName.text = data.rmName
        viewBinding.tvContactNumber.text = data.contactNumber

        viewBinding.imageview.setOnClickListener(this)
        viewBinding.tvContactNumber.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.imageview -> publishSubject.onNext(CallUiEvent.CallClick(data.contactNumber))
            v?.id == R.id.tv_contact_number -> publishSubject.onNext(CallUiEvent.CallClick(data.contactNumber))
        }
    }


}
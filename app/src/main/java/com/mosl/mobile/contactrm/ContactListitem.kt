package com.mosl.mobile.contactrm

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentContactBinding
import com.xwray.groupie.databinding.BindableItem

class ContactListitem(contactData: ContactData) : BindableItem<ItemFragmentContactBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_fragment_contact
    }

    override fun bind(viewBinding: ItemFragmentContactBinding, position: Int) {

    }


}
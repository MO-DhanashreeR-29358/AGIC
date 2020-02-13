package com.mosl.mobile.app_user.model

data class User(

    @JvmField val clientId: String,
    @JvmField val fundId: String,
    @JvmField val clientName: String,
    @JvmField val emailId: String,
    @JvmField val fundName: String,
    @JvmField val contactName1: String,
    @JvmField val contactName2: String,
    @JvmField val contactName3: String,
    @JvmField val contactMobile1: String,
    @JvmField val contactMobile2: String,
    @JvmField val contactMobile3: String,
    @JvmField val day: String,
    @JvmField val role: String ="corporate"

) {
    fun contactList(): List<ContactListItemData> {
        val contactListItemData = mutableListOf<ContactListItemData>()
        contactListItemData.apply {
            add(ContactListItemData(contactName1, contactMobile1))
            add(ContactListItemData(contactName2, contactMobile2))
            add(ContactListItemData(contactName3, contactMobile3))
        }

        val contactList = mutableListOf<ContactListItemData>()
        contactListItemData.filter { !it.contactNumber.isNullOrEmpty() }.forEach {
                contactList.add(it)
        }

        return contactList
    }

}


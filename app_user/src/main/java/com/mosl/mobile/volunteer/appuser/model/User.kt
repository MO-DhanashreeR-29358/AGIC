package com.mosl.mobile.volunteer.appuser.model

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
    @JvmField val role: String

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

data class RmUser(
    @JvmField val empCode: String,
    @JvmField val fundId: String,
    @JvmField val name: String,
    @JvmField val fundName: String,
    @JvmField val contactName1: String,
    @JvmField val contactName2: String,
    @JvmField val contactName3: String,
    @JvmField val roomNo: String,
    @JvmField val companyName: String,
    @JvmField val message: String,
)

data class Volunteer(
    @JvmField val empCode: String = "",
    @JvmField val corporateList: List<CorporateModel>,
    @JvmField val message: String
)

data class CorporateModel(
    @JvmField val corporateName: String,
    @JvmField val corpDate: String,
    @JvmField val companyId: String,
    @JvmField val shortName: String
)
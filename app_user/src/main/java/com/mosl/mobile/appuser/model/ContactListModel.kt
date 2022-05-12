package com.mosl.mobile.appuser.model

import android.os.Parcel
import android.os.Parcelable

data class ContactListItemData(
    @JvmField var rmName: String?,
    @JvmField var contactNumber: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(rmName)
        parcel.writeString(contactNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactListItemData> {
        override fun createFromParcel(parcel: Parcel): ContactListItemData {
            return ContactListItemData(parcel)
        }

        override fun newArray(size: Int): Array<ContactListItemData?> {
            return arrayOfNulls(size)
        }
    }
}
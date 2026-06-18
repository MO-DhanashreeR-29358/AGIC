package com.mosl.mobile.rm.panelpager

import android.os.Parcel
import android.os.Parcelable

data  class PanelListDayItemData (

@JvmField  val day: String?,
@JvmField  val cRepresentiveName: String?,
@JvmField  val designation: String?,
@JvmField  val representativeStarttime: String?,
@JvmField  val representativeEndtime: String?,
@JvmField  val panelImagePath: String?,
@JvmField  val speakerIntroduction: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(day)
        parcel.writeString(cRepresentiveName)
        parcel.writeString(designation)
        parcel.writeString(representativeStarttime)
        parcel.writeString(representativeEndtime)
        parcel.writeString(panelImagePath)
        parcel.writeString(speakerIntroduction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PanelListDayItemData> {
        override fun createFromParcel(parcel: Parcel): PanelListDayItemData {
            return PanelListDayItemData(parcel)
        }

        override fun newArray(size: Int): Array<PanelListDayItemData?> {
            return arrayOfNulls(size)
        }
    }
}
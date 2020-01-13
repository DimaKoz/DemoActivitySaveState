package ru.otus.demo.saveactivityinstance

import android.os.Parcel
import android.os.Parcelable


import kotlinx.android.parcel.Parcelize

@Parcelize
class PassData(val description: String) : Parcelable {

/*
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<PassData> {
        override fun createFromParcel(parcel: Parcel): PassData {
            return PassData(parcel)
        }

        override fun newArray(size: Int): Array<PassData?> {
            return arrayOfNulls(size)
        }
    }
*/

    override fun toString(): String {
        return "PassData(description='$description')"
    }

}
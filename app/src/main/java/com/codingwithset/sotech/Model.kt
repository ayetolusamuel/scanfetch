package com.codingwithset.sotech


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("description")
    var description: String,
    @SerializedName("img1")
    var img1: String,
    @SerializedName("img2")
    var img2: String,
    @SerializedName("img3")
    var img3: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("skuCode")
    var skuCode: String
): Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!) {
    }

    constructor(): this("","","","","","","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(img1)
        parcel.writeString(img2)
        parcel.writeString(img3)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(skuCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model> {
        override fun createFromParcel(parcel: Parcel): Model {
            return Model(parcel)
        }

        override fun newArray(size: Int): Array<Model?> {
            return arrayOfNulls(size)
        }
    }
}
package com.codingwithset.sotech


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
){
    constructor(): this("","","","","","","")
}
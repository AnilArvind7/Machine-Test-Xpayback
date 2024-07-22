package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?
)
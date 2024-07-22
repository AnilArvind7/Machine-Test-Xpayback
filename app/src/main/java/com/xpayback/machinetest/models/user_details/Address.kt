package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    var address: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("coordinates")
    var coordinates: Coordinates?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("postalCode")
    var postalCode: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("stateCode")
    var stateCode: String?
)
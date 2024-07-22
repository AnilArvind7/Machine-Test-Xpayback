package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Hair(
    @SerializedName("color")
    var color: String?,
    @SerializedName("type")
    var type: String?
)
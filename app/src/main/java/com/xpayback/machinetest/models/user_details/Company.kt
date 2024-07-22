package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("address")
    var address: Address?,
    @SerializedName("department")
    var department: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("title")
    var title: String?
)
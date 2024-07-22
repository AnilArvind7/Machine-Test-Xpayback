package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("coin")
    var coin: String?,
    @SerializedName("network")
    var network: String?,
    @SerializedName("wallet")
    var wallet: String?
)
package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("cardExpire")
    var cardExpire: String?,
    @SerializedName("cardNumber")
    var cardNumber: String?,
    @SerializedName("cardType")
    var cardType: String?,
    @SerializedName("currency")
    var currency: String?,
    @SerializedName("iban")
    var iban: String?
)
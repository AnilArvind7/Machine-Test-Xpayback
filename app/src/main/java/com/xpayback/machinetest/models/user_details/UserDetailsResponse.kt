package com.xpayback.machinetest.models.user_details


import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("address")
    var address: Address?,
    @SerializedName("age")
    var age: Int?,
    @SerializedName("bank")
    var bank: Bank?,
    @SerializedName("birthDate")
    var birthDate: String?,
    @SerializedName("bloodGroup")
    var bloodGroup: String?,
    @SerializedName("company")
    var company: Company?,
    @SerializedName("crypto")
    var crypto: Crypto?,
    @SerializedName("ein")
    var ein: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("eyeColor")
    var eyeColor: String?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("hair")
    var hair: Hair?,
    @SerializedName("height")
    var height: Double?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("ip")
    var ip: String?,
    @SerializedName("lastName")
    var lastName: String?,
    @SerializedName("macAddress")
    var macAddress: String?,
    @SerializedName("maidenName")
    var maidenName: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("ssn")
    var ssn: String?,
    @SerializedName("university")
    var university: String?,
    @SerializedName("userAgent")
    var userAgent: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("weight")
    var weight: Double?
)
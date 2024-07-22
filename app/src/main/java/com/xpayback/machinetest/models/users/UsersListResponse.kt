package com.xpayback.machinetest.models.users


import com.google.gson.annotations.SerializedName

data class UsersListResponse(
    @SerializedName("users")
    val users: List<User>?,
    @SerializedName("total")
    val total:Int,
    @SerializedName("skip")
    val skip:Int,
    @SerializedName("limit")
    val limit:Int

)
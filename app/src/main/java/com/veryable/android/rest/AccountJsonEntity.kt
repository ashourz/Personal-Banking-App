package com.veryable.android.rest

import com.google.gson.annotations.SerializedName
/*
* Used to serialize accounts json response
* */
data class AccountJsonEntity(
    @SerializedName("id") val id : Int,
    @SerializedName("account_type") val account_type : String,
    @SerializedName("account_name") val account_name : String,
    @SerializedName("desc") val desc : String
)
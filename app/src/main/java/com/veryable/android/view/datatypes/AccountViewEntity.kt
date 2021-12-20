package com.veryable.android.view.datatypes

/*
* Data class formatted for UI needs
* */
data class AccountViewEntity(
    var rowId: Int,
    var accountId: Int,
    var transferType: String,
    var accountName: String ,
    var desc: String,
    var icon: Int
)
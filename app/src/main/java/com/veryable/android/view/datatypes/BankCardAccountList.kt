package com.veryable.android.view.datatypes

/*
* Dataclass used to pass filtered bank and card account lists in UI format through live data stream
* */
data class BankCardAccountList(
    val bankAccountList: List<AccountViewEntity>,
    val cardAccountList: List<AccountViewEntity>
)

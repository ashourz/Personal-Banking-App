package com.veryable.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.veryable.android.repository.DatabaseRepository
import com.veryable.android.room.AccountRoomEntity
import com.veryable.android.view.datatypes.AccountViewEntity
import com.veryable.android.view.datatypes.BankCardAccountList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map

/*
* DataViewModel
* View Model layer controls requests to database and livedata streams to UI
* */
class DataViewModel(application: Application) : AndroidViewModel(application) {
    //ViewScope
    private val viewScope = viewModelScope.plus(CoroutineName("viewModelCoroutine") + SupervisorJob() + Dispatchers.Default)
    //Repositories
    private val databaseRepository : DatabaseRepository = DatabaseRepository(application)
    //LiveData
    var lifeDataAllAccountsSorted : LiveData<BankCardAccountList> = databaseRepository.flowAllAccountsSorted.map {
    accountsRoomEntityToBankCardList(it) }.asLiveData()
    init {
    }

    //Function used to format database entity to better suit UI layer before passing to livedata stream
    private fun accountsRoomEntityToBankCardList(accountsList: List<AccountRoomEntity>): BankCardAccountList {
        //Filter AccountRoomEntity List and filter by bank type
        val bankAccounts = accountsList.filter { it.account_type == "bank"}.map {
            //Map Results to Account View Entity for view consumption
            AccountViewEntity(
                rowId = it.rowid,
                accountId = it.account_id,
                accountName = it.account_name,
                transferType = "Bank Account: ACH-Same Day",
                desc = it.desc,
                icon = R.drawable.ic_darkblue_account_balance_24
            )
        }.sortedBy { it.accountId }

        //Filter AccountRoomEntity List and filter by card type
        val cardAccounts = accountsList.filter { it.account_type == "card"}.map{
            //Map Results to Account View Entity for view consumption
            AccountViewEntity(
                rowId = it.rowid,
                accountId = it.account_id,
                accountName = it.account_name,
                transferType = "Card: Instant",
                desc = it.desc,
                icon = R.drawable.ic_darkblue_credit_card_24
            )
        }.sortedBy { it.accountId }

        return BankCardAccountList(bankAccountList = bankAccounts, cardAccountList = cardAccounts)
    }

    //Call to accounts api to pull accounts information and populate database
    fun updateLiveDataAllAccounts(){
        viewScope.launch { databaseRepository.populateAccountsTable() }
    }
}
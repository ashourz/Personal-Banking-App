package com.veryable.android.repository

import android.app.Application
import com.veryable.android.rest.AccountsApiService
import com.veryable.android.room.AccountDAO
import com.veryable.android.room.AccountDatabase
import com.veryable.android.room.AccountRoomEntity
import kotlinx.coroutines.flow.Flow

/*
*Database Repository consolidates data source functions for data persistence and making api calls
* */
class DatabaseRepository(application : Application) {
    //DAO
    private var accountDAO : AccountDAO
    private val accountsApiService : AccountsApiService = AccountsApiService()
    //Mutex Single Runner
    private val databaseSingleRunner : SingleRunner = SingleRunner()
    //Flows
    var flowAllAccountsSorted : Flow<List<AccountRoomEntity>>

    init {
        val accountDatabase : AccountDatabase = AccountDatabase.getInstance(application.applicationContext)!!
        accountDAO = accountDatabase.accountDAO()
        //Flow provides live update to all accounts in database to be observed by ui
        flowAllAccountsSorted = accountDAO.flowAllAccountsSorted()

    }

    //Submits HTTP request, formats api response into data entities and stores in database
    suspend fun populateAccountsTable(): Int {
        val accountsJsonEntityList = accountsApiService.getVeryableAccounts()
        if(!accountsJsonEntityList.isNullOrEmpty()){
            val accountRoomEntityList = accountsJsonEntityList.map {
                AccountRoomEntity(
                    account_id = it.id,
                    account_name = it.account_name,
                    account_type = it.account_type,
                    desc = it.desc)
            }
            return databaseSingleRunner.afterPrevious { return@afterPrevious accountDAO.insertAll(accountRoomEntityList)}
        }else{
            return -1
        }
    }

}
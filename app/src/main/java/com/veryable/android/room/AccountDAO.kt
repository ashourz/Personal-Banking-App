package com.veryable.android.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDAO {
    //Get All Accounts
    @Query("SELECT * FROM accounts_table ORDER BY account_id DESC")
    fun flowAllAccountsSorted() : Flow<List<AccountRoomEntity>>

    //Returns Long value rowId of Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount( accountRoomEntity: AccountRoomEntity): Long

    suspend fun insertAll(entityList: List<AccountRoomEntity>): Int{
        var insertCount = 0
        entityList.forEach {
            val returnVal = insertAccount(it)
            if(returnVal >= 0){
                insertCount++
            }
        }
        return insertCount
    }

    //Returns Int value of number of rows removes
    @Query("DELETE FROM accounts_table")
    suspend fun deleteAll():Int

}
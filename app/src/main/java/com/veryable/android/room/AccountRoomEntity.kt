package com.veryable.android.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "accounts_table", indices = [Index(value = ["account_id"], unique = true)])
data class AccountRoomEntity (
    @PrimaryKey(autoGenerate = true)
    @NotNull @ColumnInfo(name = "rowid") var rowid : Int = 0,
    @NotNull @ColumnInfo(name = "account_id") var account_id: Int,
    @NotNull @ColumnInfo(name = "account_type") var account_type: String,
    @NotNull @ColumnInfo(name = "account_name") var account_name: String,
    @NotNull @ColumnInfo(name = "desc") var desc: String
)
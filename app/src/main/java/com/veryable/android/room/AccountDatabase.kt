package com.veryable.android.room

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/*
* Accounts Database class
* */
@Database(entities = [AccountRoomEntity::class], views = [], version = 1, exportSchema = false)
@TypeConverters()
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDAO(): AccountDAO

    companion object {
        //
        private var INSTANCE: AccountDatabase? = null

        //Maintain current database instance if exists
        fun getInstance(context: Context): AccountDatabase? {
            if (INSTANCE == null) {
                synchronized(Database::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AccountDatabase::class.java, "account_database"
                        )
                            //Allows room to recreate database if schema is not found
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }
}
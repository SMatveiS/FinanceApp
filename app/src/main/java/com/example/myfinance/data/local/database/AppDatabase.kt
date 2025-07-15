package com.example.myfinance.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        CategoryEntity::class,
        TransactionEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
}
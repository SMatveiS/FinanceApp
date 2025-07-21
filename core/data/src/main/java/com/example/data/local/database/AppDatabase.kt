package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.database.pending_operation.PendingOperationDao
import com.example.data.local.database.pending_operation.PendingOperationEntity

@Database(
    version = 1,
    entities = [
        CategoryEntity::class,
        TransactionEntity::class,
        PendingOperationEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun pendingOperationDao(): PendingOperationDao
}
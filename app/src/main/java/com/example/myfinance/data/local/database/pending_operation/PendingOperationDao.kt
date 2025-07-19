package com.example.myfinance.data.local.database.pending_operation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PendingOperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOperation(operation: PendingOperationEntity)

    @Update
    suspend fun updateOperation(operation: PendingOperationEntity)

    @Query("DELETE FROM pending_operations WHERE id = :id")
    suspend fun deleteOperation(id: Int)

    @Query("SELECT * FROM pending_operations ORDER BY updated_at ASC LIMIT 1")
    suspend fun getNextOperation(): PendingOperationEntity?

    @Query("DELETE FROM pending_operations WHERE id = " +
            "(SELECT id FROM pending_operations ORDER BY updated_at ASC LIMIT 1)")
    suspend fun deleteFirstOperation()
}
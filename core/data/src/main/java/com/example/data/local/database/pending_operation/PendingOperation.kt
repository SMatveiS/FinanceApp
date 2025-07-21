package com.example.data.local.database.pending_operation

import com.example.data.model.TransactionRequestDto

sealed class PendingOperation {
    data class Add(val id: Int, val transaction: TransactionRequestDto) : PendingOperation()
    data class Update(val id: Int, val transaction: TransactionRequestDto) : PendingOperation()
    data class Delete(val id: Int) : PendingOperation()
}

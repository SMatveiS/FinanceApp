package com.example.myfinance.data.local.database.pending_operation

import com.example.myfinance.data.model.TransactionRequestDto

sealed class PendingOperation {
    data class Add(val id: Int, val transaction: TransactionRequestDto) : PendingOperation()
    data class Update(val id: Int, val transaction: TransactionRequestDto) : PendingOperation()
    data class Delete(val id: Int) : PendingOperation()
}

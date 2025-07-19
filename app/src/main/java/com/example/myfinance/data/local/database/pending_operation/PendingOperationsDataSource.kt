package com.example.myfinance.data.local.database.pending_operation

import javax.inject.Inject

class PendingOperationsDataSource @Inject constructor(
    private val pendingOperationDao: PendingOperationDao
) {

    suspend fun addOperation(operation: PendingOperation) {

        when (operation) {
            is PendingOperation.Add -> pendingOperationDao.addOperation(
                PendingOperationEntity(
                    type = "ADD",
                    id = operation.id,
                    accountId = operation.transaction.accountId,
                    categoryId = operation.transaction.categoryId,
                    amount = operation.transaction.amount.toString(),
                    transactionDate = operation.transaction.transactionDate,
                    comment = operation.transaction.comment,
                    updatedAt = System.currentTimeMillis()
                )
            )


            is PendingOperation.Update -> {
                if (operation.id > 0) {
                    pendingOperationDao.addOperation(
                        PendingOperationEntity(
                            type = "UPDATE",
                            id = operation.id,
                            accountId = operation.transaction.accountId,
                            categoryId = operation.transaction.categoryId,
                            amount = operation.transaction.amount.toString(),
                            transactionDate = operation.transaction.transactionDate,
                            comment = operation.transaction.comment,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                } else { // Если эта операция была создана без интернета (id < 0) то обновляем все поля, кроме type

                    pendingOperationDao.updateOperation(
                        PendingOperationEntity(
                            type = "ADD",
                            id = operation.id,
                            accountId = operation.transaction.accountId,
                            categoryId = operation.transaction.categoryId,
                            amount = operation.transaction.amount.toString(),
                            transactionDate = operation.transaction.transactionDate,
                            comment = operation.transaction.comment,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                }
            }

            // Если операция была создана без интернета (id > 0), то удаляем её из очереди
            is PendingOperation.Delete -> {
                if (operation.id > 0) {
                    pendingOperationDao.addOperation(
                        PendingOperationEntity(
                            type = "DELETE",
                            id = operation.id,
                            accountId = null,
                            categoryId = null,
                            amount = null,
                            transactionDate = null,
                            comment = null,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                } else {
                    pendingOperationDao.deleteOperation(operation.id)
                }
            }
        }
    }

    suspend fun getNextOperation(): PendingOperation? {

        val entity = pendingOperationDao.getNextOperation() ?: return null

        return when (entity.type) {
            "ADD" -> PendingOperation.Add(entity.id, entity.toDto())
            "UPDATE" -> PendingOperation.Update(entity.id, entity.toDto())
            "DELETE" -> PendingOperation.Delete(entity.id)
            else -> null
        }
    }

    suspend fun removeOperation() {
        pendingOperationDao.deleteFirstOperation()
    }
}
package com.example.myfinance.domain.usecase.transaction

import com.example.myfinance.data.utils.NetworkResult
import com.example.myfinance.ui.feature.presentation.transactions_history.viewmodel.TransactionsResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Возвращает доходы или расходы и их сумму за текущий день
 *
 * Транзакции отсортированы по времени: чем новее, тем раньше в списке
 *
 * isIncomes: Boolean - если true, то возвращает доходы, иначе - расходы
 */

class GetTodayTransactionsUseCase @Inject constructor(
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase
){

    suspend operator fun invoke(isIncomes: Boolean): NetworkResult<TransactionsResult> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("y-MM-dd"))

        return getTransactionsForPeriodUseCase(today, today, isIncomes)
    }
}

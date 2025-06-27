package com.example.myfinance.feature.domain.usecase

import com.example.myfinance.feature.domain.model.Transaction
import com.example.myfinance.feature.utils.NetworkResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetTodayTransactionsUseCase @Inject constructor(
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase
){

    suspend operator fun invoke(): NetworkResult<List<Transaction>> {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("y-MM-dd"))

        return getTransactionsForPeriodUseCase(today, today)
    }
}
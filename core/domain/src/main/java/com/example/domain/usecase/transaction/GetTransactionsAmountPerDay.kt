package com.example.domain.usecase.transaction

import com.example.model.DailyAmount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetDailyAmounts @Inject constructor(
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase
) {

    private val periodFormatter = DateTimeFormatter.ofPattern("y-MM-dd")
    private val transactionFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")
    private val outputFormatter = DateTimeFormatter.ofPattern("dd.MM")

    suspend operator fun invoke(
        startDate: String,
        endDate: String
    ): Result<List<DailyAmount>> {

        val incomes = getTransactionsForPeriodUseCase(startDate, endDate, true).getOrElse {
            return Result.failure(it)
        }
        val expenses = getTransactionsForPeriodUseCase(startDate, endDate, false).getOrElse {
            return Result.failure(it)
        }

        val start = LocalDate.parse(startDate, periodFormatter)
        val end = LocalDate.parse(endDate, periodFormatter)

        val dailyAmounts = mutableMapOf<String, Double>().apply {
            var current = start
            while (!current.isAfter(end)) {
                put(current.format(outputFormatter), 0.0)
                current = current.plusDays(1)
            }
        }

        incomes.transactions
            .groupBy { it.date }
            .forEach { (dateTime, transactions) ->
                val date = LocalDateTime.parse(dateTime, transactionFormatter).format(outputFormatter)
                dailyAmounts[date] = transactions.sumOf { it.amount }
            }

        expenses.transactions
            .groupBy { it.date }
            .forEach { (dateTime, transactions) ->
                val date = LocalDateTime.parse(dateTime, transactionFormatter).format(outputFormatter)
                val currentValue = dailyAmounts[date]
                if (currentValue != null) {
                    dailyAmounts[date] = currentValue - transactions.sumOf { it.amount }
                }
            }

        return Result.success(
            dailyAmounts.map {
                DailyAmount(
                    amount = it.value,
                    date = it.key
                )
            }
        )
    }
}
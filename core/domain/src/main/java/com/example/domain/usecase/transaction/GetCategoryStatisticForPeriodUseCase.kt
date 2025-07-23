package com.example.domain.usecase.transaction

import com.example.model.CategoryStatistic
import com.example.model.CategoryStatisticsInfo
import javax.inject.Inject
import kotlin.collections.groupBy

class GetCategoryStatisticForPeriodUseCase @Inject constructor(
    private val getTransactionsForPeriodUseCase: GetTransactionsForPeriodUseCase
) {

    suspend operator fun invoke(
        startDate: String,
        endDate: String,
        isIncomes: Boolean
    ): Result<CategoryStatisticsInfo> {

        val transactionsInfoResult = getTransactionsForPeriodUseCase(
            startDate = startDate,
            endDate = endDate,
            isIncomes = isIncomes
        )

        return transactionsInfoResult.map { transactionsInfo ->
            val transactions = transactionsInfo.transactions

            val groupedTransactions = transactions.groupBy { it.category.id }

            val categoryStatistics = mutableListOf<CategoryStatistic>()
            for (group in groupedTransactions) {
                val categorySum = group.value.sumOf { it.amount }

                categoryStatistics.add(
                    CategoryStatistic(
                        category = group.value.first().category,
                        amount = categorySum,
                        proportion = (categorySum / transactionsInfo.transactionsSum) * 100
                    )
                )
            }

            CategoryStatisticsInfo(
                categoryStatistics = categoryStatistics.sortedByDescending { it.proportion },
                totalSum = transactionsInfo.transactionsSum,
                currency = transactionsInfo.currency
            )
        }
    }
}
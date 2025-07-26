package com.example.myfinance.ui.feature.presentation.analysis.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.chart.CategoryWithProportionItem
import com.example.chart.DonutChart
import com.example.model.CategoryStatistic
import com.example.ui.AppListItem
import com.example.ui.addCurrency
import com.example.ui.formatNumber
import com.example.ui.uiDateFormat
import java.time.LocalDate

@Composable
fun AnalysisContent(
    categoryStatistics: List<CategoryStatistic>,
    currency: String,
    totalSum: Double,
    startDate: LocalDate,
    endDate: LocalDate,
    onStartDatePickerOpen: () -> Unit,
    onEndDatePickerOpen: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        AnalysisDatePickerElement(
            leftTitle = "Период: начало",
            rightTitle = startDate.format(uiDateFormat),
            onClick = onStartDatePickerOpen
        )
        HorizontalDivider()

        AnalysisDatePickerElement(
            leftTitle = "Период: конец",
            rightTitle = endDate.format(uiDateFormat),
            onClick = onEndDatePickerOpen
        )
        HorizontalDivider()

        AppListItem(
            leftTitle = "Сумма",
            rightTitle = formatNumber(totalSum).addCurrency(currency),
            itemHeight = 56
        )
        HorizontalDivider()

        if (categoryStatistics.isNotEmpty()) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {

                item {
                    DonutChart(
                        categoryStatistics.map {
                            CategoryWithProportionItem(
                                name = it.category.name,
                                proportion = it.proportion
                            )
                        }
                    )

                    HorizontalDivider()
                }

                items(categoryStatistics) { categoryStatistic ->
                    CategoryStatisticListItem(categoryStatistic, currency)
                    HorizontalDivider()
                }
            }
        } else {

            Text(
                "Нет транзакций за выбранный период",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }
    }
}
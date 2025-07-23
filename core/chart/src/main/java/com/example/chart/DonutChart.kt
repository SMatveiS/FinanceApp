package com.example.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.utils.DataUtils.getColorPaletteList
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

data class CategoryWithProportionItem(
    val name: String,
    val proportion: Double
)

@Composable
fun DonutChart(
    categories: List<CategoryWithProportionItem>
) {
    val colors = remember(categories.size) { getColorPaletteList(categories.size) }

    val donutChartData = PieChartData(
        slices = categories.mapIndexed { index, category ->
            PieChartData.Slice(
                label = category.name,
                value = category.proportion.toFloat(),
                color = colors[index]
            ) },

        plotType = PlotType.Donut
    )

    val donutChartConfig = PieChartConfig(
        showSliceLabels = true,
        strokeWidth = 20f,
        chartPadding = 20,
        isAnimationEnable = true,
        backgroundColor = MaterialTheme.colorScheme.background
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {

        DonutPieChart(
            modifier = Modifier.fillMaxSize(),
            donutChartData,
            donutChartConfig
        )

        // По центру с размером не более 120dp
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight()
        ) {
            // Прижимаем к левому краю
            Column(horizontalAlignment = Alignment.Start) {

                donutChartData.slices.forEachIndexed { index, item ->

                    // Показываем не больше пяти элементов
                    if (index < 5) {
                        Row(verticalAlignment = Alignment.CenterVertically,) {

                            Box(
                                modifier = Modifier
                                    .background(item.color, shape = CircleShape)
                                    .size(6.dp)
                            )

                            Text(
                                "${(item.value * 100).toInt() / 100.0}% ${item.label}",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 3.dp)
                            )
                        }
                    }
                }
            }

            if (categories.size > 5) {
                Text(
                    "...",
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
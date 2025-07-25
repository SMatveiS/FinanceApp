package com.example.chart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import kotlin.math.abs

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BarChart(data: List<Pair<String, Double>>, currency: String) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .height(220.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {

        val availableWidth = maxWidth
        // Отступы справа и слева
        val paddingSize = 60
        // Количество столбцов и расстояний между ними
        // (должно быть data.size столбцов и data.size - 1 пробелов, но почему-то нужный результат с data.size * 2 - 2)
        val elementsCount = data.size * 2 - 2
        // Ширина стобцов и пробелов между ними
        val barWidth = (availableWidth - paddingSize.dp) / elementsCount


        val barData = data.mapIndexed { index, (date, value) ->
            BarData(
                point = Point(index.toFloat(), abs(value).toFloat()),
                color = if (value < 0) Color(0xFF2AE881) else Color(0xFFFF5F00),
                label = date
            ) }

        val xAxisData = AxisData.Builder()
            .startDrawPadding(30.dp)
            .steps(barData.size - 1)
            .shouldDrawAxisLineTillEnd(true)
            .axisLineColor(MaterialTheme.colorScheme.onBackground)
            .axisLabelColor(MaterialTheme.colorScheme.onBackground)
            .labelData { index ->
                if (index == 0 || index == (barData.size / 2) - 1 || index == barData.size - 1) {
                    barData[index].label
                } else { "" }
            }
            .build()

        val barChartData = BarChartData(
            chartData = barData,
            xAxisData = xAxisData,
            backgroundColor = MaterialTheme.colorScheme.background,
            paddingEnd = 0.dp,
            barStyle = BarStyle(
                barWidth = barWidth,
                paddingBetweenBars = barWidth,
                selectionHighlightData = SelectionHighlightData(
                    highlightTextOffset = 5.dp,
                    highlightTextBackgroundColor = MaterialTheme.colorScheme.secondary,
                    highlightTextColor = MaterialTheme.colorScheme.onSecondary,
                    popUpLabel = { x, y ->
                        val xLabel = barData[x.toInt()].label
                        val yLabel = "${String.format("%.2f", y)} $currency"

                        "$xLabel:  $yLabel"
                    }
                )
            )
        )

        BarChart(
            barChartData = barChartData,
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.example.financetracker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun SimpleBarChart(incomeData: List<Double>, expenseData: List<Double>) {
    val maxValue = (incomeData + expenseData).maxOrNull() ?: 0.0

    // Get the colors from the MaterialTheme
    val primaryColor = MaterialTheme.colorScheme.primary.toArgb()
    val errorColor = MaterialTheme.colorScheme.error.toArgb()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val barWidth = size.width / (incomeData.size + expenseData.size).coerceAtLeast(1)

            incomeData.forEachIndexed { index, value ->
                drawRect(
                    color = androidx.compose.ui.graphics.Color(primaryColor),
                    topLeft = Offset(index * barWidth * 2, size.height - (value / maxValue * size.height).toFloat()),
                    size = Size(barWidth, (value / maxValue * size.height).toFloat())
                )
            }

            expenseData.forEachIndexed { index, value ->
                drawRect(
                    color = androidx.compose.ui.graphics.Color(errorColor),
                    topLeft = Offset((index * 2 + 1) * barWidth, size.height - (value / maxValue * size.height).toFloat()),
                    size = Size(barWidth, (value / maxValue * size.height).toFloat())
                )
            }
        }

        Text(
            "Income",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            "Expense",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}
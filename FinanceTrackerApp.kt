package com.example.financetracker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FinanceTrackerApp() {
    val viewModel: FinanceViewModel = viewModel()
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Balance: $${String.format("%.2f", viewModel.getBalance())}", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Add the simple chart
        val (incomeData, expenseData) = viewModel.getChartData()
        SimpleBarChart(incomeData, expenseData)

        Spacer(modifier = Modifier.height(16.dp))

        // Input fields
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isExpense,
                onCheckedChange = { isExpense = it }
            )
            Text("Is Expense")
        }

        Button(
            onClick = {
                viewModel.addTransaction(description, amount.toDoubleOrNull() ?: 0.0, isExpense)
                description = ""
                amount = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Transaction")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of transactions
        LazyColumn {
            items(viewModel.transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}
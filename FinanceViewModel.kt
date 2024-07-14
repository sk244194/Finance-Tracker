package com.example.financetracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FinanceViewModel : ViewModel() {
    private val _transactions = mutableStateListOf<Transaction>()
    val transactions: List<Transaction> = _transactions

    fun addTransaction(description: String, amount: Double, isExpense: Boolean) {
        _transactions.add(Transaction(_transactions.size, description, amount, isExpense))
    }

    fun getBalance(): Double {
        return transactions.sumOf { if (it.isExpense) -it.amount else it.amount }
    }

    fun getChartData(): Pair<List<Double>, List<Double>> {
        val incomeData = transactions.filter { !it.isExpense }.map { it.amount }
        val expenseData = transactions.filter { it.isExpense }.map { it.amount }
        return Pair(incomeData, expenseData)
    }
}
package com.example.financetracker

data class Transaction(
    val id: Int,
    val description: String,
    val amount: Double,
    val isExpense: Boolean
)
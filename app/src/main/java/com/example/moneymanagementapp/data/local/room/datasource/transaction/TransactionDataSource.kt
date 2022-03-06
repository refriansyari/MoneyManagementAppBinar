package com.example.moneymanagementapp.data.local.room.datasource.transaction

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

interface TransactionDataSource {

    suspend fun insertTransaction(transaction: Transaction) : Long

    suspend fun deleteTransaction(transaction: Transaction) : Int

    suspend fun updateTransaction(transaction: Transaction) : Int

    suspend fun getAllTransactions() : List<Transaction>

    suspend fun getTransactionById(id: Int) : List<Transaction>

    suspend fun getCategoryWithTransaction(categoryName: String): List<CategoriesWithTransaction>
}
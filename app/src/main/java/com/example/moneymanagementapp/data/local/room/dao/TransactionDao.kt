package com.example.moneymanagementapp.data.local.room.dao

import androidx.room.*
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction) : Long

    @Delete
    suspend fun deleteTransaction(transaction: Transaction) : Int

    @Update
    suspend fun updateTransaction(transaction: Transaction) : Int

    @Query("SELECT * FROM transaction_table")
    suspend fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transaction_table WHERE id = :id")
    suspend fun getTransactionById(id: Int): List<Transaction>

    @Query("SELECT SUM(transactionAmount) FROM transaction_table WHERE transactionType = 'Income'")
    suspend fun getTotalIncome(): Double

    @Query("SELECT SUM(transactionAmount) FROM transaction_table WHERE transactionType = 'Expense'")
    suspend fun getTotalExpense(): Double

    @Query("SELECT (SELECT SUM(transactionAmount) FROM transaction_table WHERE transactionType = 'Income') - (SELECT SUM(transactionAmount) FROM transaction_table WHERE transactionType = 'Expense')")
    suspend fun getTotalAmount(): Double

    @Query("SELECT * FROM categories_table WHERE categoryName = :categoryName")
    suspend fun getCategoryWithTransaction(categoryName: String): List<CategoriesWithTransaction>
}
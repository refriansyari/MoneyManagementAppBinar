package com.example.moneymanagementapp.data.local.room.datasource.transaction

import com.example.moneymanagementapp.data.local.room.dao.TransactionDao
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

class TransactionDataSourceImpl(private val dao: TransactionDao): TransactionDataSource {
    override suspend fun insertTransaction(transaction: Transaction): Long {
        return dao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction): Int {
        return dao.deleteTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: Transaction): Int {
        return dao.updateTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<Transaction> {
        return dao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): List<Transaction> {
        return dao.getTransactionById(id)
    }

    override suspend fun getCategoryWithTransaction(categoryName: String): List<CategoriesWithTransaction> {
        return dao.getCategoryWithTransaction(categoryName)
    }
}
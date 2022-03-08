package com.example.moneymanagementapp.ui.transactionpage.transactionform

import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSource
import com.example.moneymanagementapp.data.local.room.entity.Transaction

class TransactionFormRepository(private val transactionsDataSource: TransactionDataSource,
    ) : TransactionFormContract.Repository {
    override suspend fun insertTransaction(transaction: Transaction): Long {
        return transactionsDataSource.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction): Int {
        return transactionsDataSource.deleteTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: Transaction): Int {
        return transactionsDataSource.updateTransaction(transaction)
    }
}
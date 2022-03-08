package com.example.moneymanagementapp.ui.transactionpage.transactionlist

import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSource
import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

class TransactionListRepository(private val transactionDataSource: TransactionDataSourceImpl) : TransactionContract.Repository {

    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionDataSource.getAllTransactions()
    }

    override suspend fun getCategoryWithTransaction(): List<CategoriesWithTransaction> {
        return transactionDataSource.getCategoryWithTransaction(categoryName = String())
    }

    override suspend fun getTotalIncome(): Double {
        return transactionDataSource.getTotalIncome()
    }

    override suspend fun getTotalExpense(): Double {
        return transactionDataSource.getTotalExpense()
    }

    override suspend fun getTotalAmount(): Double {
        return transactionDataSource.getTotalAmount()
    }
}
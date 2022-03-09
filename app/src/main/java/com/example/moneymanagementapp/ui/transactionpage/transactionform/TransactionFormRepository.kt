package com.example.moneymanagementapp.ui.transactionpage.transactionform

import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSource
import com.example.moneymanagementapp.data.local.room.datasource.transaction.TransactionDataSource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

class TransactionFormRepository(private val transactionsDataSource: TransactionDataSource, private val categoriesDataSource: CategoriesDataSource
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

    override suspend fun getIncomeCategories(): List<Categories> {
        return categoriesDataSource.getIncomeCategories()
    }

    override suspend fun getExpenseCategories(): List<Categories> {
        return categoriesDataSource.getExpenseCategories()
    }

    override suspend fun getIncomeTitle(): List<String> {
        return categoriesDataSource.getIncomeTitle()
    }

    override suspend fun getExpenseTitle(): List<String> {
        return categoriesDataSource.getExpenseTitle()
    }

}
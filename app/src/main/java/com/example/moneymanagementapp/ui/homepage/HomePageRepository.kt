package com.example.moneymanagementapp.ui.homepage

import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSourceImpl
import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.ui.homepage.HomeContract.Repository

class HomePageRepository(private val transactionDataSource: TransactionDataSourceImpl) :
    HomeContract.Repository {


    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionDataSource.getAllTransactions()
    }

    override suspend fun getTotalIncome(): Double {
        return transactionDataSource.getTotalIncome()
    }

    override suspend fun getTotalExpense(): Double {
        return transactionDataSource.getTotalExpense()
    }


    override fun logResponse(msg: String?) {
        TODO("Not yet implemented")
    }
}
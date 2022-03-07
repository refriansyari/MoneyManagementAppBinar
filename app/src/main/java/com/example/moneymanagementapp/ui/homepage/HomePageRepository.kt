package com.example.moneymanagementapp.ui.homepage

import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.ui.homepage.HomeContract.Repository

class HomePageRepository(private val categoriesDataSource: CategoriesDataSource) : HomeContract.Repository {
    override suspend fun getIncomeCategories(): List<Categories> {
        TODO("Not yet implemented")
    }

    override suspend fun getOutcomeCategories(): List<Categories> {
        TODO("Not yet implemented")
    }


    override fun logResponse(msg: String?) {
        TODO("Not yet implemented")
    }
}
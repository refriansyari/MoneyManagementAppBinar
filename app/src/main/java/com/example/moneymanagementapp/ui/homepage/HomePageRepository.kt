package com.example.moneymanagementapp.ui.homepage

import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.ui.homepage.HomeContract.Repository

class HomePageRepository(private val categoriesDataSource: CategoriesDataSource) :
    HomeContract.Repository {


    override suspend fun getOutcomeCategories(): List<Categories> {
        return categoriesDataSource.getExpenseCategories()
    }


    override fun logResponse(msg: String?) {
        TODO("Not yet implemented")
    }
}
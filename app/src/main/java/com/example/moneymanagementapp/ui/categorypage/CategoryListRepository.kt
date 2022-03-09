package com.example.moneymanagementapp.ui.categorypage

import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Categories

class CategoryListRepository(private val categoriesDataSource: CategoriesDataSourceImpl) :
    CategoryListContract.Repository {
    override suspend fun getIncomeCategories(): List<Categories> {
        return categoriesDataSource.getIncomeCategories()
    }

    override suspend fun getExpenseCategories(): List<Categories> {
        return categoriesDataSource.getExpenseCategories()
    }
}
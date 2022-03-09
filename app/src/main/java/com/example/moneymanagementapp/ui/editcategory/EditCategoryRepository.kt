package com.example.moneymanagementapp.ui.editcategory

import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSource
import com.example.moneymanagementapp.data.local.room.entity.Categories

class EditCategoryRepository(
    private val categoriesDataSource: CategoriesDataSource
) :
    EditCategoryContract.Repository {
    override suspend fun insertCategory(category: Categories): Long {
        return categoriesDataSource.insertCategory(category)
    }

    override suspend fun updateCategory(category: Categories) :Int {
        return categoriesDataSource.updateCategory(category)
    }

    override suspend fun deleteCategory(category: Categories) : Int{
        return categoriesDataSource.deleteCategory(category)
    }


}
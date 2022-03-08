package com.example.moneymanagementapp.data.local.room.datasource

import com.example.moneymanagementapp.data.local.room.dao.CategoriesDao
import com.example.moneymanagementapp.data.local.room.entity.Categories

class CategoriesDataSourceImpl (private val dao : CategoriesDao) : CategoriesDataSource
    {
    override suspend fun insertCategory(category: Categories): Long {
        return dao.insertCategory(category)
    }

    override suspend fun updateCategory(category: Categories): Int {
        return dao.updateCategory(category)
    }

    override suspend fun deleteCategory(category: Categories): Int {
        return dao.deleteCategory(category)
    }

    override suspend fun getIncomeCategories(): List<Categories> {
        return dao.getIncomeCategories()
    }

    override suspend fun getExpenseCategories(): List<Categories> {
        return dao.getExpenseCategories()
    }

   /* override suspend fun getCategoriesById(id: Int): List<Categories> {
        return dao.getCategoriesById(id)
    }*/
}
package com.example.moneymanagementapp.data.local.room.dao

import androidx.room.*
import com.example.moneymanagementapp.data.local.room.entity.Categories

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insertCategory(category: Categories): Long

    @Update
    suspend fun updateCategory(category: Categories): Int

    @Delete
    suspend fun deleteCategory(category: Categories): Int

    @Query("SELECT * FROM categories_table WHERE id = :id")
    suspend fun getCategoryById(id: Int): List<Categories>

    @Query("SELECT * from categories_table WHERE categoryType = 'Income'")
    suspend fun getIncomeCategories(): List<Categories>

    @Query("SELECT * from categories_table WHERE categoryType = 'Expense'")
    suspend fun getExpenseCategories(): List<Categories>
}
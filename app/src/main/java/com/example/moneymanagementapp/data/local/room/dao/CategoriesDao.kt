package com.example.moneymanagementapp.data.local.room.dao

import androidx.room.*
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insertCategory(category: Categories): Long

    @Update
    suspend fun updateCategory(category: Categories): Int

    @Delete
    suspend fun deleteCategory(category: Categories): Int

    @Query("SELECT * from categories_table WHERE categoryType = 'INCOME' ORDER BY id DESC")
    fun getIncomeCategories(): List<Categories>

    @Query("SELECT * from categories_table WHERE categoryType = 'EXPENSE' ORDER BY id DESC")
    fun getExpenseCategories(): List<Categories>

    @Query("SELECT categoryName from categories_table WHERE categoryType = 'INCOME'")
    fun getIncomeTitle(): List<String>

    @Query("SELECT categoryName from categories_table WHERE categoryType = 'EXPENSE'")
    fun getExpenseTitle(): List<String>

   /* @Query("SELECT * FROM categories_table WHERE ID == id")
    suspend fun getCategoriesById(id: Int): List<Categories>*/
}
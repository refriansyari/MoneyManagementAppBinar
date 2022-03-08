package com.example.moneymanagementapp.data.local.room.dao

import androidx.room.*
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insertCategory(category: Categories) : Long

    @Update
    suspend fun updateCategory(category: Categories) : Int

    @Delete
    suspend fun deleteCategory(category: Categories) : Int

    @Query("SELECT * from categories_table WHERE categoryType = 'Income'")
    fun getIncomeCategories() : List<Categories>

    @Query("SELECT * from categories_table WHERE categoryType = 'Expense'")
    fun getExpenseCategories() : List<Categories>
}
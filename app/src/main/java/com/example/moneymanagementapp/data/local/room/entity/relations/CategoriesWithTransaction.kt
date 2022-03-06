package com.example.moneymanagementapp.data.local.room.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

data class CategoriesWithTransaction(
    @Embedded val category: Categories,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "categoryName"
    )
    val transaction: List<Transaction>
)

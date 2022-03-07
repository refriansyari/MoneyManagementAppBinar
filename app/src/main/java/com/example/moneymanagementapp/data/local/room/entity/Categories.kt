package com.example.moneymanagementapp.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories_table")
data class Categories(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "categoryName")
    var categoryName: String,
    @ColumnInfo(name = "categoryType")
    var categoryType: String
) : Parcelable

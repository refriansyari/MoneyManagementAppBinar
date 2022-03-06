package com.example.moneymanagementapp.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "transactionTitle")
    val transactionTitle: String,
    @ColumnInfo(name = "transactionType")
    val transactionType: String,
    @ColumnInfo(name = "transactionDetails")
    val transactionDetails: String,
    @ColumnInfo(name = "transactionAmount")
    val transactionAmount: Double,
    @ColumnInfo(name = "categoryName")
    val categoryName: String

) : Parcelable

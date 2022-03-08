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
    var transactionTitle: String,
    @ColumnInfo(name = "transactionType")
    val transactionType: String,
    @ColumnInfo(name = "transactionDetails")
    val transactionDetails: String?,
    @ColumnInfo(name = "transactionAmount")
    var transactionAmount: Double?,
    @ColumnInfo(name = "categoryName")
    var categoryName: String

) : Parcelable

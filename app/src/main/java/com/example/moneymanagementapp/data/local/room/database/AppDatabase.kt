package com.example.moneymanagementapp.data.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moneymanagementapp.data.local.room.dao.CategoriesDao
import com.example.moneymanagementapp.data.local.room.dao.TransactionDao
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

@Database(entities = [Transaction::class, Categories::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoriesDao(): CategoriesDao

    companion object {
        private const val DB_NAME = "app_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).addCallback(dbCallback).build()
                INSTANCE = instance
                instance
            }
        }
        private val dbCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (0, 'Gaji', 'INCOME')")
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (1, 'Omzet', 'INCOME')")
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (2, 'Bonus', 'INCOME')")
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (3, 'Belanja', 'EXPENSE')")
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (4, 'Makanan', 'EXPENSE')")
                db.execSQL("INSERT INTO categories_table (id, categoryName, categoryType) VALUES (5, 'Transportasi', 'EXPENSE')")
            }
        }
    }
}
package com.example.moneymanagementapp.ui.transactionpage.transactionform

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

interface TransactionFormContract {
    interface View : BaseContract.BaseView {
        fun getIntentData()
        fun showToast(msg: String)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun insertTransaction(transaction: Transaction)
        fun deleteTransaction(transaction: Transaction)
        fun updateTransaction(transaction: Transaction)
        fun getTransactionResultLiveData(): MutableLiveData<Pair<String, Resource<Number>>>

        fun getIncomeCategories()
        fun getExpenseCategories()
        fun getCategoriesLiveData() : MutableLiveData<Resource<List<Categories>>>
        fun getIncomeLiveData(): MutableLiveData<List<String>>
        fun getExpenseLiveData(): MutableLiveData<List<String>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun insertTransaction(transaction: Transaction): Long
        suspend fun deleteTransaction(transaction: Transaction): Int
        suspend fun updateTransaction(transaction: Transaction): Int

        suspend fun getIncomeCategories(): List<Categories>
        suspend fun getExpenseCategories(): List<Categories>

        suspend fun getIncomeTitle(): List<String>
        suspend fun getExpenseTitle(): List<String>

    }
}
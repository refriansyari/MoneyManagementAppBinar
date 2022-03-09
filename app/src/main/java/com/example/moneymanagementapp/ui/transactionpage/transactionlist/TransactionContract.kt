package com.example.moneymanagementapp.ui.transactionpage.transactionlist

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

interface TransactionContract {
    interface View : BaseContract.BaseView{
        fun setupRecyclerView()
        fun setupSwipeRefresh()
        fun setListData(data: List<Transaction>)
        fun getData()
        fun getDataCategories()
        fun updateDashboard()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getAllTransactions()
        fun getCategoryWithTransactions()
        fun getNotesLiveData(): MutableLiveData<Resource<List<Transaction>>>
        fun getIncomeLiveData(): MutableLiveData<Double>
        fun getExpenseLiveData(): MutableLiveData<Double>
        fun getAmountLiveData(): MutableLiveData<Double>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllTransactions(): List<Transaction>
        suspend fun getCategoryWithTransaction(): List<CategoriesWithTransaction>
        suspend fun getTotalIncome(): Double
        suspend fun getTotalExpense(): Double
        suspend fun getTotalAmount(): Double
    }

}
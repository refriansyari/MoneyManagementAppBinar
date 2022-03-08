package com.example.moneymanagementapp.ui.transactionpage.transactionlist

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction

interface TransactionContract {
    interface View : BaseContract.BaseView{
        fun setupRecyclerView()
        fun setupSwipeRefresh()
        fun setListData(data: List<Transaction>)
        fun getData()
        fun getDataCategories()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getAllTransactions()
        fun getCategoryWithTransactions()
        fun getNotesLiveData(): MutableLiveData<Resource<List<Transaction>>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllTransactions(): List<Transaction>
        suspend fun getCategoryWithTransaction(): List<CategoriesWithTransaction>
    }

}
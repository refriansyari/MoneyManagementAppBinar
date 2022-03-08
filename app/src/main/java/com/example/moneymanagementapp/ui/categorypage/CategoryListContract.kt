package com.example.moneymanagementapp.ui.categorypage

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories

interface CategoryListContract {
    interface View : BaseContract.BaseView {
        fun setupRecyclerView()
        fun setupSwipeRefresh()
        fun setListData(data: List<Categories>)
        fun getData()
        fun showDialogEdit(note: Categories)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getIncomeCategories()
        fun getExpenseCategories()
        fun getCategoriesLiveData() : MutableLiveData<Resource<List<Categories>>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getIncomeCategories(): List<Categories>
        suspend fun getExpenseCategories(): List<Categories>
    }
}
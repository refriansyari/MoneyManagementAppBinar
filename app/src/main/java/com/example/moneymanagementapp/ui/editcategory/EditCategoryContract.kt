package com.example.moneymanagementapp.ui.editcategory

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories

interface EditCategoryContract {
    interface View : BaseContract.BaseView {
        fun getIntentData()
        fun showToast(msg: String)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun insertCategory(category: Categories)
        fun updateCategory(category: Categories)
        fun deleteCategory(category: Categories)
        fun getCategoriesLiveData(): MutableLiveData<Pair<String, Resource<Number>>>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun insertCategory(category: Categories): Long
        suspend fun updateCategory(category: Categories): Int
        suspend fun deleteCategory(category: Categories): Int

    }
}
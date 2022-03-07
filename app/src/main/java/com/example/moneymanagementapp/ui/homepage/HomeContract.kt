package com.example.moneymanagementapp.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories

interface HomeContract {
    interface View : BaseContract.BaseView {
        fun setupPieChart()
        fun setDataPieChart()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getIncomeCategories()
        fun getOutcomeCategories()
        fun getCategoriesLiveData(): MutableLiveData<Resource<List<Categories>>>

    }

    interface Repository : BaseContract.BaseViewModel {
        suspend fun getIncomeCategories(): List<Categories>
        suspend fun getOutcomeCategories(): List<Categories>

    }

}
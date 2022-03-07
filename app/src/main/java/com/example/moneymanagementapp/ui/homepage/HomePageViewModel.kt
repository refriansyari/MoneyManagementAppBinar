package com.example.moneymanagementapp.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories

class HomePageViewModel(private val repository: HomePageRepository) : BaseViewModelImpl(), HomeContract.ViewModel {
    override fun getIncomeCategories() {
        TODO("Not yet implemented")
    }

    override fun getOutcomeCategories() {
        TODO("Not yet implemented")
    }

    override fun getCategoriesLiveData(): MutableLiveData<Resource<List<Categories>>> {
        TODO("Not yet implemented")
    }
}
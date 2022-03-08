package com.example.moneymanagementapp.ui.categorypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoryListViewModel(private val repository: CategoryListContract.Repository) :
    BaseViewModelImpl(), CategoryListContract.ViewModel {

    private val categoriesData = MutableLiveData<Resource<List<Categories>>>()

    override fun getIncomeCategories() {
        // Main Thread
        categoriesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            //IO Thread
            try {
                delay(2500)
                val categories = repository.getIncomeCategories()
                viewModelScope.launch(Dispatchers.Main) {
                    // Main Thread and set the data
                    categoriesData.value = Resource.Success(categories)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    categoriesData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }

    }

    override fun getExpenseCategories() {
        //Main Thread
        categoriesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            //IO Thread
            try {
                delay(2500)
                val categories = repository.getExpenseCategories()
                viewModelScope.launch(Dispatchers.Main) {
                    // Main Thread and set the data
                    categoriesData.value = Resource.Success(categories)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    categoriesData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getCategoriesLiveData(): MutableLiveData<Resource<List<Categories>>> = categoriesData

}
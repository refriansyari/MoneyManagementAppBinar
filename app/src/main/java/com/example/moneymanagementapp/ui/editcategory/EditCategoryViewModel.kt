package com.example.moneymanagementapp.ui.editcategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_DELETE
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_INSERT
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_UPDATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class EditCategoryViewModel(private val repository: EditCategoryRepository) : BaseViewModelImpl(),
    EditCategoryContract.ViewModel {

    private val categoryResultLiveData = MutableLiveData<Pair<String, Resource<Number>>>()
    override fun insertCategory(category: Categories) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val insertedRowId = repository.insertCategory(category)
                viewModelScope.launch(Dispatchers.Main) {
                    if (insertedRowId > 0) {
                        categoryResultLiveData.value =
                            Pair(ACTION_INSERT, Resource.Success(insertedRowId))
                    } else {
                        categoryResultLiveData.value =
                            Pair(ACTION_INSERT,Resource.Error("",insertedRowId))
                    }
                }
            }catch (exception : Exception){
                viewModelScope.launch (Dispatchers.Main){
                    categoryResultLiveData.value =
                        Pair(ACTION_INSERT,Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun updateCategory(category: Categories) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val affectedRow = repository.updateCategory(category)
                viewModelScope.launch(Dispatchers.Main) {
                    if (affectedRow > 0) {
                        categoryResultLiveData.value =
                            Pair(ACTION_UPDATE, Resource.Success(affectedRow))
                    } else {
                        categoryResultLiveData.value =
                            Pair(ACTION_UPDATE,Resource.Error("",affectedRow))
                    }
                }
            }catch (exception : Exception){
                viewModelScope.launch (Dispatchers.Main){
                    categoryResultLiveData.value =
                        Pair(ACTION_UPDATE,Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun deleteCategory(category: Categories) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val affectedRow = repository.deleteCategory(category)
                viewModelScope.launch(Dispatchers.Main) {
                    if (affectedRow > 0) {
                        categoryResultLiveData.value =
                            Pair(ACTION_DELETE, Resource.Success(affectedRow))
                    } else {
                        categoryResultLiveData.value =
                            Pair(ACTION_DELETE,Resource.Error("",affectedRow))
                    }
                }
            }catch (exception : Exception){
                viewModelScope.launch (Dispatchers.Main){
                    categoryResultLiveData.value =
                        Pair(ACTION_DELETE,Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun getCategoriesLiveData(): MutableLiveData<Pair<String, Resource<Number>>> =
        categoryResultLiveData
}
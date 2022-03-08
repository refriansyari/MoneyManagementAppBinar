package com.example.moneymanagementapp.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class HomePageViewModel(private val repository: HomePageRepository) : BaseViewModelImpl(),
    HomeContract.ViewModel {
    private val transactionsData = MutableLiveData<Resource<List<Transaction>>>()
    private val getTotalIncome = MutableLiveData<Double>()
    private val getTotalExpense = MutableLiveData<Double>()

    fun getTotalIncomeFun() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getTotalIncome()
            viewModelScope.launch(Dispatchers.Main) {
                getTotalIncome.value = data
            }
        }
    }

    fun getTotalExpenseFun() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getTotalExpense()
            viewModelScope.launch(Dispatchers.Main) {
                getTotalExpense.value = data
            }
        }
    }


    override fun getAllTransactions() {
        //mainthread
        transactionsData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            //io thread
            try {
                delay(2000)
                val transactions = repository.getAllTransactions()
                viewModelScope.launch(Dispatchers.Main) {
                    //main thread
                    //set the data
                    transactionsData.value = Resource.Success(transactions)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    //set the error message
                    transactionsData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }


    override fun getTransactionLiveData(): MutableLiveData<Resource<List<Transaction>>> =
        transactionsData
    override fun getIncomeLiveData(): MutableLiveData<Double> = getTotalIncome
    override fun getExpenseLiveData(): MutableLiveData<Double> = getTotalExpense

}
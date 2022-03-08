package com.example.moneymanagementapp.ui.transactionpage.transactionlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.data.local.room.entity.relations.CategoriesWithTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransactionListViewModel(private val repository: TransactionListRepository): BaseViewModelImpl(),
TransactionContract.ViewModel {

    private val transactionsData = MutableLiveData<Resource<List<Transaction>>>()
    private val categoriesData = MutableLiveData<Resource<List<CategoriesWithTransaction>>>()

    //get income, expense, amount
    private val getTotalIncome = MutableLiveData<Double>()
    private val getTotalExpense = MutableLiveData<Double>()
    private val getTotalAmount = MutableLiveData<Double>()

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

    fun getTotalAmountFun() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getTotalAmount()
            viewModelScope.launch(Dispatchers.Main) {
                getTotalAmount.value = data
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
            }catch (e : Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    //set the error message
                    transactionsData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getCategoryWithTransactions() {
        //mainthread
        categoriesData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            //io thread
            try {
                delay(2000)
                val transactions = repository.getCategoryWithTransaction()
                viewModelScope.launch(Dispatchers.Main) {
                    //main thread
                    //set the data
                    categoriesData.value = Resource.Success(transactions)
                }
            }catch (e : Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    //set the error message
                    categoriesData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }


    override fun getNotesLiveData(): MutableLiveData<Resource<List<Transaction>>> = transactionsData

    override fun getIncomeLiveData(): MutableLiveData<Double> = getTotalIncome
    override fun getExpenseLiveData(): MutableLiveData<Double> = getTotalExpense
    override fun getAmountLiveData(): MutableLiveData<Double> = getTotalAmount

}
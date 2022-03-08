package com.example.moneymanagementapp.ui.transactionpage.transactionform


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_DELETE
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_INSERT
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_UPDATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionFormViewModel(private val repository: TransactionFormRepository) :
    BaseViewModelImpl(),
    TransactionFormContract.ViewModel {

    private val transactionResultLiveData = MutableLiveData<Pair<String, Resource<Number>>>()


    override fun insertTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val insertedRowId = repository.insertTransaction(transaction)
                viewModelScope.launch(Dispatchers.Main) {
                    if (insertedRowId > 0) {
                       transactionResultLiveData.value =
                            Pair(ACTION_INSERT, Resource.Success(insertedRowId))
                    } else {
                        transactionResultLiveData.value =
                            Pair(ACTION_INSERT, Resource.Error("", insertedRowId))
                    }
                }
            } catch (exception: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    transactionResultLiveData.value =
                        Pair(ACTION_INSERT, Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val affectedRow = repository.deleteTransaction(transaction)
                viewModelScope.launch(Dispatchers.Main) {
                    if (affectedRow > 0) {
                        transactionResultLiveData.value =
                            Pair(ACTION_DELETE, Resource.Success(affectedRow))
                    } else {
                        transactionResultLiveData.value =
                            Pair(ACTION_DELETE, Resource.Error("", affectedRow))
                    }
                }
            } catch (exception: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    transactionResultLiveData.value =
                        Pair(ACTION_DELETE, Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val affectedRow = repository.updateTransaction(transaction)
                viewModelScope.launch(Dispatchers.Main) {
                    if (affectedRow > 0) {
                        transactionResultLiveData.value =
                            Pair(ACTION_UPDATE, Resource.Success(affectedRow))
                    } else {
                        transactionResultLiveData.value =
                            Pair(ACTION_UPDATE, Resource.Error("", affectedRow))
                    }
                }
            } catch (exception: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    transactionResultLiveData.value =
                        Pair(ACTION_UPDATE, Resource.Error(exception.message.orEmpty()))
                }
            }
        }
    }

    override fun getTransactionResultLiveData(): MutableLiveData<Pair<String, Resource<Number>>> =
        transactionResultLiveData

}
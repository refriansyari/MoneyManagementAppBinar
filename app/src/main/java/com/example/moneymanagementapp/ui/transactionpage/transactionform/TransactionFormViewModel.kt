package com.example.moneymanagementapp.ui.transactionpage.transactionform


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moneymanagementapp.base.arch.BaseViewModelImpl
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_DELETE
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_INSERT
import com.example.moneymanagementapp.utils.CommonConstant.ACTION_UPDATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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



    private val categoriesData = MutableLiveData<Resource<List<Categories>>>()
    private val getIncomeCategoriesTitle = MutableLiveData<List<String>>()
    private val getExpenseCategoriesTitle = MutableLiveData<List<String>>()

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
            } catch (e: java.lang.Exception) {
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
            } catch (e: java.lang.Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    categoriesData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }



    fun getIncomeList(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getIncomeTitle()
            viewModelScope.launch(Dispatchers.Main) {
                getIncomeCategoriesTitle.value = data
            }
        }
    }

    fun getExpenseList(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getExpenseTitle()
            viewModelScope.launch(Dispatchers.Main) {
                getExpenseCategoriesTitle.value = data
            }
        }
    }


    override fun getCategoriesLiveData(): MutableLiveData<Resource<List<Categories>>> = categoriesData
    override fun getIncomeLiveData(): MutableLiveData<List<String>> = getIncomeCategoriesTitle
    override fun getExpenseLiveData(): MutableLiveData<List<String>> = getExpenseCategoriesTitle


}
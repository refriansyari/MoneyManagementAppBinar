package com.example.moneymanagementapp.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.data.local.room.entity.Transaction

interface HomeContract {
    interface View : BaseContract.BaseView {
        fun setupPieChart()
        fun setDataPieChart(data: List<Transaction>)
        fun getData()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getIncomeLiveData(): MutableLiveData<Double>
        fun getExpenseLiveData(): MutableLiveData<Double>
        fun getAllTransactions()
        fun getTransactionLiveData(): MutableLiveData<Resource<List<Transaction>>>

    }

    interface Repository : BaseContract.BaseViewModel {
        suspend fun getAllTransactions(): List<Transaction>
        suspend fun getTotalIncome(): Double
        suspend fun getTotalExpense(): Double

    }

}
package com.example.moneymanagementapp.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.example.moneymanagementapp.base.arch.BaseContract
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.entity.Categories

interface HomeContract {
    interface View : BaseContract.BaseView {
        fun setupPieChart()


    }

    interface ViewModel : BaseContract.BaseViewModel {
    }

    interface Repository : BaseContract.BaseViewModel {
    }
}
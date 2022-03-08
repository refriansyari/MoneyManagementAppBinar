package com.example.moneymanagementapp.ui.transactionpage.transactionlist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Database
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.base.arch.BaseFragment
import com.example.moneymanagementapp.base.arch.GenericViewModelFactory
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.database.AppDatabase
import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.databinding.FragmentTransactionBinding
import com.example.moneymanagementapp.ui.transactionpage.transactionform.TransactionFormActivity
import com.example.moneymanagementapp.ui.transactionpage.transactionlist.adapter.TransactionAdapter
import java.util.*


class TransactionListFragment :
    BaseFragment<FragmentTransactionBinding, TransactionListViewModel>(FragmentTransactionBinding::inflate),
    TransactionContract.View {

    private lateinit var adapter: TransactionAdapter
    private var transaction: Transaction? = null

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun initView() {
        setupRecyclerView()
        setupSwipeRefresh()
        getViewBinding().floatingActionButton.setOnClickListener {
            TransactionFormActivity.startActivity(context, TransactionFormActivity.FORM_MODE_INSERT)
        }

    }


//    fun setCurrencyInRupiah() {
//        var localeId = Locale("in", "ID")
//        var formatRupiah = NumberFormat.getCurrencyInstance(localeId)
//        transaction?.let {
//            val amountInput = getViewBinding().totalExpense
//            amountInput.setText(formatRupiah.format(it.transactionAmount))
//        }
//    }

    override fun initViewModel(): TransactionListViewModel {
        val dataSource = TransactionDataSourceImpl(AppDatabase.getInstance(requireContext()).transactionDao())
        val repository = TransactionListRepository(dataSource)
        return GenericViewModelFactory(TransactionListViewModel(repository)).create(TransactionListViewModel::class.java)
    }

    override fun setupRecyclerView() {
        adapter = TransactionAdapter {
            TransactionFormActivity.startActivity(context, TransactionFormActivity.FORM_MODE_EDIT, it)
        }
        getViewBinding().rvTransactions.apply {
            layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
            adapter = this@TransactionListFragment.adapter
        }
    }

    override fun setupSwipeRefresh() {
        getViewBinding().srlTransactions.setOnRefreshListener {
            getData()
            getViewBinding().srlTransactions.isRefreshing = false
        }
    }

    override fun setListData(data: List<Transaction>) {
        adapter.setItems(data)
    }

    override fun getData() {
        getViewModel().getAllTransactions()
    }

    override fun getDataCategories() {
        getViewModel().getCategoryWithTransactions()
    }


    override fun showLoading(isLoading: Boolean) {
        super.showLoading(isLoading)
        getViewBinding().layoutScenario.progressBar.isVisible = isLoading
    }

    override fun showContent(isContentVisible: Boolean) {
        super.showContent(isContentVisible)
        getViewBinding().rvTransactions.isVisible = isContentVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().layoutScenario.tvMessage.isVisible = isErrorEnabled
        getViewBinding().layoutScenario.tvMessage.text = msg

    }




    override fun observeData() {
        getViewModel().getNotesLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let { notes ->
                        if (notes.isEmpty()) {
                            showError(true, getString(R.string.text_empty_transactions))
                            showContent(false)
                        } else {
                            showError(false, null)
                            showContent(true)
                            setListData(notes)
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, it.message)
                    showContent(false)
                }
            }
        }
    }

}
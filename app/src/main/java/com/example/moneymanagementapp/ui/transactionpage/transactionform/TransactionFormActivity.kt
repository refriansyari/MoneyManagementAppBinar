package com.example.moneymanagementapp.ui.transactionpage.transactionform

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.base.arch.BaseActivity
import com.example.moneymanagementapp.base.arch.GenericViewModelFactory
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.database.AppDatabase
import com.example.moneymanagementapp.data.local.room.datasource.TransactionDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.databinding.ActivityTransactionFormBinding
import com.example.moneymanagementapp.utils.CommonConstant
import java.text.NumberFormat
import java.util.*

class TransactionFormActivity :
    BaseActivity<ActivityTransactionFormBinding, TransactionFormViewModel>(
        ActivityTransactionFormBinding::inflate
    ),
    TransactionFormContract.View {

    private var formMode = FORM_MODE_INSERT
    private var transaction: Transaction? = null

    companion object {
        const val FORM_MODE_INSERT = 0
        const val FORM_MODE_EDIT = 1
        const val INTENT_FORM_MODE = "INTENT_FORM_MODE"
        const val INTENT_TRANSACTION_DATA = "INTENT_TRANSACTION_DATA"

        @JvmStatic
        fun startActivity(context: Context?, formMode: Int, transaction: Transaction? = null) {
            val intent = Intent(context, TransactionFormActivity::class.java)
            intent.putExtra(INTENT_FORM_MODE, formMode)
            transaction?.let {
                intent.putExtra(INTENT_TRANSACTION_DATA, transaction)
            }
            context?.startActivity(intent)
        }
    }


    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getIntentData()
        initializeForm()
        onAddButtonClicked()
        setCurrencyInRupiah()
        spinnerAdapter()
    }


    override fun observeData() {
        super.observeData()
        getViewModel().getTransactionResultLiveData().observe(this) {
            when (it.first) {
                CommonConstant.ACTION_INSERT -> {
                    if (it.second is Resource.Success) {
                        showToast("Insert data Success!")
                    } else {
                        showToast("Insert data Failed!")
                    }
                }
                CommonConstant.ACTION_DELETE -> {
                    if (it.second is Resource.Success) {
                        showToast("Delete data Success!")
                    } else {
                        showToast("Delete data Failed!")
                    }
                }
                CommonConstant.ACTION_UPDATE -> {
                    if (it.second is Resource.Success) {
                        showToast("Update data Success!")
                    } else {
                        showToast("Update data Failed!")
                    }
                }
            }

            finish()
        }
    }

    private fun spinnerAdapter(){
        val spinner: Spinner = findViewById(R.id.spinner_category)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }



    private fun initializeForm() {
        if (formMode == FORM_MODE_EDIT) {
            getViewBinding().btnTransaction.text = "Edit Transaction"
            transaction?.let {
                getViewBinding().etLabelInput.setText(it.transactionTitle)
                getViewBinding().etAmountInput.setText(it.transactionAmount.toString())
            }
            //"Edit Data"
            supportActionBar?.title = "Edit Transaction"
        } else {
            supportActionBar?.title = "Insert Transaction"
            getViewBinding().btnDeleteTransaction.visibility = View.INVISIBLE
        }
    }

    override fun initViewModel(): TransactionFormViewModel {
        val repository = TransactionFormRepository(
            TransactionDataSourceImpl(AppDatabase.getInstance(this).transactionDao())
        )
        return GenericViewModelFactory(TransactionFormViewModel(repository)).create(
            TransactionFormViewModel::class.java
        )
    }

    private fun setCurrencyInRupiah() {
        var localeId = Locale("in", "ID")
        var formatRupiah = NumberFormat.getCurrencyInstance(localeId)
        transaction?.let {
            val amountInput = getViewBinding().etAmountInput
            amountInput.setText(formatRupiah.format(it.transactionAmount))
        }
    }


    override fun getIntentData() {
        formMode = intent.getIntExtra(INTENT_FORM_MODE, FORM_MODE_INSERT)
        transaction = intent.getParcelableExtra(INTENT_TRANSACTION_DATA)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun onAddButtonClicked() {
        getViewBinding().btnTransaction.setOnClickListener {
            saveNote()
        }
        if (formMode == FORM_MODE_EDIT) {
            getViewBinding().btnDeleteTransaction.setOnClickListener {
                deleteNote()
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                true
            }
        }
    }


    private fun validateForm(): Boolean {
        val title = getViewBinding().etLabelInput.text.toString()
        val amount = getViewBinding().etAmountInput.text.toString()
        var isFormValid = true
        //for checking is title empty
        if (title.isEmpty() || amount.isEmpty()) {
            isFormValid = false
            getViewBinding().etLabel.isErrorEnabled = true
            getViewBinding().etLabel.error = "Please Enter the Label"
            getViewBinding().etAmount.isErrorEnabled = true
            getViewBinding().etAmount.error = "Please Enter the Amount"
        } else {
            getViewBinding().etLabel.isErrorEnabled = false
        }
        return isFormValid
    }

    private fun deleteNote() {
        if (formMode == FORM_MODE_EDIT) {
            transaction?.let {
                getViewModel().deleteTransaction(it)
            }
        }
    }


    private fun saveNote() {
        if (validateForm()) {
            if (formMode == FORM_MODE_EDIT) {
                // do edit
                transaction = transaction?.copy()?.apply {
                    transactionTitle = getViewBinding().etLabelInput.text.toString()
                    transactionAmount = getViewBinding().etAmountInput.text.toString().toDoubleOrNull()
                    categoryName = getViewBinding().spinnerCategory.toString()
                }

                transaction?.let {
                    getViewModel().updateTransaction(it)
                }
            } else {
                //do insert
                val checkedIncomeRadioButtonId = getViewBinding().rgType.checkedRadioButtonId
                val income = findViewById<RadioButton>(checkedIncomeRadioButtonId)

                transaction = Transaction(
                    id = 0,
                    transactionTitle = getViewBinding().etLabelInput.text.toString(),
                    transactionAmount = getViewBinding().etAmountInput.text.toString().toDoubleOrNull(),
                    categoryName = getViewBinding().spinnerCategory.toString(),
                    transactionDetails = null,
                    transactionType = income.text.toString()
                )

                transaction?.let {
                    getViewModel().insertTransaction(it)
                }

            }
        }
    }
}
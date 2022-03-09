package com.example.moneymanagementapp.ui.editcategory

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.Toast
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.base.arch.BaseActivity
import com.example.moneymanagementapp.base.arch.GenericViewModelFactory
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.database.AppDatabase
import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.databinding.ActivityEditCategoryBinding
import com.example.moneymanagementapp.utils.CommonConstant


class EditCategoryActivity :
    BaseActivity<ActivityEditCategoryBinding, EditCategoryViewModel>(
        ActivityEditCategoryBinding::inflate
    ),
    EditCategoryContract.View {

    private var editMode = FORM_MODE_UPDATE
    private var category: Categories? = null


    companion object {
        const val FORM_MODE_INSERT = 0
        const val FORM_MODE_UPDATE = 1
        const val INTENT_FORM_MODE = "INTENT_FORM_MODE"
        const val INTENT_CATEGORY_DATA = "INTENT_CATEGORY_DATA"

        @JvmStatic
        fun startActivity(context: Context?, editMode: Int, category: Categories? = null) {
            val intent = Intent(context, EditCategoryActivity::class.java)
            intent.putExtra(INTENT_FORM_MODE, editMode)
            category?.let {
                intent.putExtra(INTENT_CATEGORY_DATA, category)
            }
            context?.startActivity(intent)
        }
    }


    override fun getIntentData() {
        editMode = intent.getIntExtra(INTENT_FORM_MODE, FORM_MODE_INSERT)
        category = intent.getParcelableExtra(INTENT_CATEGORY_DATA)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getIntentData()
        initializeForm()
        setClickListeners()

    }

    private fun setClickListeners() {
        getViewBinding().btnSave.setOnClickListener {
            saveCategory()
        }
        getViewBinding().btnCancel.setOnClickListener {
            onBackPressed()
        }
    }


    override fun initViewModel(): EditCategoryViewModel {
        val repository = EditCategoryRepository(
            CategoriesDataSourceImpl(AppDatabase.getInstance(this).categoriesDao())
        )
        return GenericViewModelFactory(EditCategoryViewModel(repository)).create(
            EditCategoryViewModel::class.java
        )
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getCategoriesLiveData().observe(this) {
            when (it.first) {
                CommonConstant.ACTION_INSERT -> {
                    if (it.second is Resource.Success) {
                        showToast(getString(R.string.text_category_added))
                    } else {
                        showToast(getString(R.string.text_category_failed))
                    }

                }
                CommonConstant.ACTION_DELETE -> {
                    if (it.second is Resource.Success) {
                        showToast(getString(R.string.text_delete_success))
                    } else {
                        showToast(getString(R.string.text_delete_failed))
                    }
                }
                CommonConstant.ACTION_UPDATE -> {
                    if (it.second is Resource.Success) {
                        showToast(getString(R.string.text_update_success))
                    } else {
                        showToast(getString(R.string.text_update_failed))
                    }
                }
            }
            finish()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu_categories, menu)
        val menuDelete = menu?.findItem(R.id.menu_delete)
        menuDelete?.isVisible = editMode == FORM_MODE_UPDATE
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                deleteCategory()
                true
            }
            androidx.appcompat.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                true
            }
        }
    }


    private fun validateForm(): Boolean {
        val name = getViewBinding().etCategory.text.toString()
        var isFormValid = true

        if (name.isEmpty()) {
            isFormValid = false
            Toast.makeText(this, "Please Add Name Category", Toast.LENGTH_SHORT).show()
        }
        return isFormValid


    }

    private fun deleteCategory() {
        if (editMode == FORM_MODE_UPDATE) {
            category?.let {
                getViewModel().deleteCategory(it)
            }
        }

    }

    private fun saveCategory() {

        when (getViewBinding().rgCategoryType.checkedRadioButtonId) {
            R.id.rb_income -> {
                category?.categoryType = "INCOME"
            }
            R.id.rb_expense -> {
                category?.categoryType = "EXPENSE"
            }
        }

        if (validateForm()) {
            if (editMode == FORM_MODE_UPDATE) {
                category = category?.copy()?.apply {
                    categoryName = getViewBinding().etCategory.text.toString()
                    categoryType
                }
                category?.let { getViewModel().updateCategory(it) }
            }
             else {

                when (getViewBinding().rgCategoryType.checkedRadioButtonId) {
                    R.id.rb_income -> {
                        category?.categoryType = "INCOME"
                    }
                    R.id.rb_expense -> {
                        category?.categoryType = "EXPENSE"
                    }
                }

                category = Categories(
                    id = 0,
                    categoryName = getViewBinding().etCategory.text.toString(),
                    categoryType = category?.categoryType ?: "INCOME"
                )
                category?.let { getViewModel().insertCategory(it) }
            } }

    }

    private fun initializeForm() {
//        if (editMode == FORM_MODE_UPDATE) {
//            category = category?.copy().apply {
//                getViewBinding().etCategory.setText(this?.categoryName)
//                if (category?.categoryType == "INCOME") {
//                    getViewBinding().tvOptionExpense.setBackgroundResource(R.drawable.bg_text_options_form)
//                    getViewBinding().tvOptionExpense.resources.getColor(R.color.white)
//                }else{
//                    getViewBinding().tvOptionIncome.setBackgroundResource(R.drawable.bg_text_options_form)
//                    getViewBinding().tvOptionIncome.resources.getColor(R.color.white)
//
//                }
//            }
//            supportActionBar?.title = getString(R.string.titlebar_edit_category)
//        } else {
//
//            supportActionBar?.title = getString(R.string.titlebar_new_category)
//        }

        if (editMode == FORM_MODE_UPDATE) {
            category = category?.copy().apply {
                getViewBinding().tvAddName.text = "Edit Category"
                getViewBinding().btnDeleteCategory.visibility = View.VISIBLE
                getViewBinding().etCategory.setText(category?.categoryName.toString())
                getViewBinding().btnDeleteCategory.setOnClickListener {
                    category?.let {
                        getViewModel().deleteCategory(it)
                    }
                }
            }
            supportActionBar?.title = getString(R.string.titlebar_edit_category)
        } else {

            supportActionBar?.title = getString(R.string.titlebar_new_category)
        }
    }
}



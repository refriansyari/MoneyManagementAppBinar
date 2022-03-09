package com.example.moneymanagementapp.ui.categorypage

import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.base.arch.BaseFragment
import com.example.moneymanagementapp.base.arch.GenericViewModelFactory
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.database.AppDatabase
import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSourceImpl
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.databinding.FragmentCategoryIncomeBinding
import com.example.moneymanagementapp.databinding.FragmentCategoryListBinding
import com.example.moneymanagementapp.ui.categorypage.adapter.CategoriesAdapter
import com.example.moneymanagementapp.ui.categorypage.adapter.ViewPagerAdapter
import com.example.moneymanagementapp.ui.editcategory.EditCategoryActivity
import com.example.moneymanagementapp.ui.editcategory.EditCategoryActivity.Companion.FORM_MODE_INSERT
import com.example.moneymanagementapp.utils.CommonFunction
import com.example.moneymanagementapp.utils.SpacesItemDecoration
import com.google.android.material.tabs.TabLayoutMediator

class CategoryIncomeFragment() :
    BaseFragment<FragmentCategoryIncomeBinding, CategoryListViewModel>(FragmentCategoryIncomeBinding::inflate),
    CategoryListContract.View {

    private lateinit var adapter: CategoriesAdapter

    override fun initView() {

        setupRecyclerView()
        setupSwipeRefresh()
        getViewBinding().ivAddCategory.setOnClickListener {
            EditCategoryActivity.startActivity(context, FORM_MODE_INSERT)
        }
    }


    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun initViewModel(): CategoryListViewModel {
        val dataSource = CategoriesDataSourceImpl(
            AppDatabase.getInstance(requireContext()).categoriesDao()
        )
        val repository = CategoryListRepository(dataSource)
        return GenericViewModelFactory(CategoryListViewModel(repository)).create(
            CategoryListViewModel::class.java
        )
    }

    override fun setupRecyclerView() {
        adapter = CategoriesAdapter {
            if (it.categoryType == "INCOME"){
                showDialogEdit(it)
            }else if (it.categoryType == "EXPENSE"){
                showDialogEdit(it)
            }
        }
        getViewBinding().rvCategories.apply {
            layoutManager = StaggeredGridLayoutManager(1,LinearLayoutManager.VERTICAL)
            addItemDecoration(SpacesItemDecoration(CommonFunction.dpToPixels(requireContext(),2)))
            adapter = this@CategoryIncomeFragment.adapter
        }
    }

    override fun setupSwipeRefresh() {
        getViewBinding().srlCategories.setOnRefreshListener {
            getData()
            getViewBinding().srlCategories.isRefreshing = false
        }
    }

    override fun setListData(data: List<Categories>) {
        adapter.setItems(data)
    }

    override fun getData() {
        getViewModel().getIncomeCategories()
    }

    override fun showDialogEdit(note: Categories) {
        EditCategoryActivity.startActivity(context,EditCategoryActivity.FORM_MODE_UPDATE,note)
    }

    override fun showLoading(isVisible: Boolean) {
        super.showLoading(isVisible)
        getViewBinding().layoutScenario.progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        super.showContent(isVisible)
        getViewBinding().rvCategories.isVisible = isVisible
        getViewBinding().ivAddCategory.setOnClickListener {
            EditCategoryActivity.startActivity(context, FORM_MODE_INSERT)
        }
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().layoutScenario.tvMessage.isVisible = isErrorEnabled
        getViewBinding().layoutScenario.tvMessage.text = msg
    }

    override fun observeData() {
        getViewModel().getCategoriesLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let { categories ->
                        if (categories.isEmpty()) {
                            showError(true, "No Categories Available")
                            showContent(false)
                        } else {
                            showError(false, null)
                            showContent(true)
                            setListData(categories)
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
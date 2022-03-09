package com.example.moneymanagementapp.ui.categorypage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneymanagementapp.ui.categorypage.CategoryExpenseFragment
import com.example.moneymanagementapp.ui.categorypage.CategoryIncomeFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CategoryIncomeFragment()
            1 -> CategoryExpenseFragment()
            else -> Fragment()
        }
    }
}
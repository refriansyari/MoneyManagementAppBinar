package com.example.moneymanagementapp.ui.categorypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.databinding.FragmentCategoryBinding
import com.example.moneymanagementapp.ui.categorypage.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.vpViewPager.adapter = adapter
        binding.vpViewPager.isSaveEnabled = false



        TabLayoutMediator(binding.tlTabLayout, binding.vpViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Income"
                }
                1 -> {
                    tab.text = "Expense"
                }
            }
        }.attach()
    }

}
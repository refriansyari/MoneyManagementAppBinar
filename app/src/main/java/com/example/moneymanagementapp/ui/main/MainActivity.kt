package com.example.moneymanagementapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.databinding.ActivityMainBinding
import com.example.moneymanagementapp.ui.aboutpage.AboutFragment
import com.example.moneymanagementapp.ui.categorypage.CategoryListFragment
import com.example.moneymanagementapp.ui.homepage.HomeFragment
import com.example.moneymanagementapp.ui.transactionpage.TransactionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()

        val homeFragment = HomeFragment()
        val transactionPage = TransactionFragment()
        val categoryFragment = CategoryListFragment()
        val aboutFragment = AboutFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mHome -> setCurrentFragment(homeFragment)
                R.id.mTransaction -> setCurrentFragment(transactionPage)
                R.id.mCategory -> setCurrentFragment(categoryFragment)
                R.id.mAbout -> setCurrentFragment(aboutFragment)
            }
            true
        }


    }

    private fun bindViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }


}
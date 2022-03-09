package com.example.moneymanagementapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.databinding.ActivityMainBinding
import com.example.moneymanagementapp.ui.aboutpage.AboutFragment
import com.example.moneymanagementapp.ui.categorypage.CategoryFragment
import com.example.moneymanagementapp.ui.homepage.HomeFragment
import com.example.moneymanagementapp.ui.transactionpage.transactionlist.TransactionListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()

        val homeFragment = HomeFragment()
        val transactionPage = TransactionListFragment()
        val categoryFragment = CategoryFragment()
//        val aboutFragment = AboutFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mHome -> {
                    setCurrentFragment(homeFragment)
                    supportActionBar?.show()
                }
                R.id.mTransaction -> {
                    setCurrentFragment(transactionPage)
                    supportActionBar?.hide()
                }
                R.id.mCategory -> {
                    setCurrentFragment(categoryFragment)
                    supportActionBar?.hide()
                }
//                R.id.mAbout -> {
//                    setCurrentFragment(aboutFragment)
//                    supportActionBar?.hide()
//                }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        openMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun openMenu(selectedMenu: Int) {
        when(selectedMenu){
            R.id.action_about -> {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.text_alert_dialog_title))
                    .setMessage(getString(R.string.text_alert_dialog_message))
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
        }
    }


}
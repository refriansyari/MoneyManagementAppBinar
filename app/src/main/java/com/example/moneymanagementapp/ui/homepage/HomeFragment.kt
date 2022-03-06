package com.example.moneymanagementapp.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moneymanagementapp.R

import com.example.moneymanagementapp.base.arch.BaseFragment
import com.example.moneymanagementapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend

class HomeFragment : BaseFragment<FragmentHomeBinding,HomePageViewModel>(FragmentHomeBinding::inflate),
    HomeContract.View {

    private lateinit var pieChart: PieChart


    override fun initView() {
        setupPieChart()
    }

    override fun initViewModel(): HomePageViewModel {
        TODO("Not yet implemented")
    }

    override fun setupPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = "Income"
        //hollow pie chart
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
    }


}
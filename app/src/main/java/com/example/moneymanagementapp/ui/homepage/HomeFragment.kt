package com.example.moneymanagementapp.ui.homepage


import android.graphics.Color
import androidx.core.view.isVisible
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.base.arch.BaseFragment
import com.example.moneymanagementapp.base.arch.GenericViewModelFactory
import com.example.moneymanagementapp.base.model.Resource
import com.example.moneymanagementapp.data.local.room.database.TransactionDatabase
import com.example.moneymanagementapp.data.local.room.datasource.category.CategoriesDataSourceImpl
import com.example.moneymanagementapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class HomeFragment : BaseFragment<FragmentHomeBinding,HomePageViewModel>(FragmentHomeBinding::inflate),
    HomeContract.View {
    
    override fun initView() {
        setupPieChart()
        setDataPieChart()
    }

    override fun initViewModel(): HomePageViewModel {
        val dataSoure = CategoriesDataSourceImpl(TransactionDatabase.getInstance(requireContext()).categoriesDao())
        val repository = HomePageRepository(dataSoure)
        return GenericViewModelFactory(HomePageViewModel(repository)).create(HomePageViewModel::class.java)
    }

    override fun setupPieChart() {
        getViewBinding().piechart.setUsePercentValues(true)
        //hollow pie chart
        getViewBinding().piechart.isDrawHoleEnabled = false
        getViewBinding().piechart.setTouchEnabled(false)
        getViewBinding().piechart.setDrawEntryLabels(false)
        //adding padding
        getViewBinding().piechart.setExtraOffsets(20f, 0f, 20f, 20f)
        getViewBinding().piechart.setUsePercentValues(true)
        getViewBinding().piechart.isRotationEnabled = false
        getViewBinding().piechart.setDrawEntryLabels(false)
        getViewBinding().piechart.legend.orientation = Legend.LegendOrientation.VERTICAL
        getViewBinding().piechart.legend.isWordWrapEnabled = true
    }

    override fun setDataPieChart() {
        getViewBinding().piechart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(72f, "Working Salary"))
        dataEntries.add(PieEntry(26f, "Side Job Salary"))
        dataEntries.add(PieEntry(2f, "Other"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        getViewBinding().piechart.data = data
        data.setValueTextSize(15f)
        getViewBinding().piechart.setExtraOffsets(5f, 10f, 5f, 5f)
        getViewBinding().piechart.animateY(1400, Easing.EaseInOutQuad)

        //create hole in center
        getViewBinding().piechart.holeRadius = 58f
        getViewBinding().piechart.transparentCircleRadius = 61f
        getViewBinding().piechart.isDrawHoleEnabled = true
        getViewBinding().piechart.setHoleColor(Color.WHITE)


        //add text in center
        getViewBinding().piechart.setDrawCenterText(true);
        getViewBinding().piechart.centerText = "Income by Categories"



        getViewBinding().piechart.invalidate()
    }

//    override fun observeData() {
//        getViewModel().getCategoriesLiveData().observe(this) {
//            when (it) {
//                is Resource.Loading -> {
//                    showLoading(true)
//                    showError(false, null)
//                    showContent(false)
//                }
//                is Resource.Success -> {
//                    showLoading(false)
//                    it.data?.let { notes ->
//                        if (notes.isEmpty()) {
//                            showError(true, "No Data Available")
//                            showContent(false)
//                        } else {
//                            showError(false, null)
//                            showContent(true)
////                            setListData(notes)
//                        }
//                    }
//                }
//                is Resource.Error -> {
//                    showLoading(false)
//                    showError(true, it.message)
//                    showContent(false)
//                }
//
//            }
//        }
//    }

    override fun showLoading(isLoading: Boolean) {
        super.showLoading(isLoading)
        getViewBinding().layoutScenario.progressBar.isVisible = isLoading
    }

    override fun showContent(isContentVisible: Boolean) {
        super.showContent(isContentVisible)
        getViewBinding().piechart.isVisible = isContentVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().layoutScenario.tvMessage.isVisible = isErrorEnabled
        getViewBinding().layoutScenario.tvMessage.text = msg
    }

}
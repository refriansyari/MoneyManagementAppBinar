package com.example.moneymanagementapp.ui.categorypage.adapter;

import android.graphics.drawable.GradientDrawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.data.local.room.entity.Categories
import com.example.moneymanagementapp.databinding.ItemExpenseBinding
import com.example.moneymanagementapp.databinding.ItemIncomeBinding
import com.example.moneymanagementapp.utils.CommonFunction
import com.example.moneymanagementapp.utils.dpToPixels


class CategoriesAdapter(private val itemClick: (Categories) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_INCOME = 0
        const val ITEM_EXPENSE = 1
    }


    private var items: MutableList<Categories> = mutableListOf()

    fun setItems(items: List<Categories>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Categories>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return return if (viewType == ITEM_INCOME) {
            val binding =
                ItemIncomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            IncomeViewHolder(binding, itemClick)
        } else {
            val binding =
                ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ExpenseViewHolder(binding, itemClick)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is IncomeViewHolder) {
            holder.bindView(items[position])
        } else if (holder is ExpenseViewHolder) {
            holder.bindView(items[position])
        }

    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        if (items[position].categoryType == "EXPENSE")
            ITEM_EXPENSE
        else
            ITEM_INCOME


    class IncomeViewHolder(
        private val binding: ItemIncomeBinding,
        val itemClick: (Categories) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Categories) {
            with(item) {
                binding.tvCategories.text = item.categoryName
                binding.root.background = GradientDrawable().apply {
                    shape = GradientDrawable.LINE
                    cornerRadius = 8.dpToPixels(itemView.context).toFloat()
                    mutate()
                }
                itemView.setOnClickListener { itemClick(this) }
            }

        }

    }

    class ExpenseViewHolder(
        private val binding: ItemExpenseBinding,
        val itemClick: (Categories) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bindView(item: Categories) {
            with(item) {
                binding.tvCategories.text = item.categoryName
                binding.root.background = GradientDrawable().apply {
                    shape = GradientDrawable.LINE
                    cornerRadius = 8.dpToPixels(itemView.context).toFloat()
                    mutate()
                }
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

}
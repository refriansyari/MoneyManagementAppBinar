package com.example.moneymanagementapp.ui.transactionpage.transactionlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.data.local.room.entity.Transaction
import com.example.moneymanagementapp.databinding.ItemTransactionBinding
import java.text.NumberFormat
import java.util.*


class TransactionAdapter(private val itemClick: (Transaction) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TransactionViewHolder) {
            holder.bindView(items[position])
        }


    }

    override fun getItemCount(): Int = items.size

    class TransactionViewHolder(
        private val binding: ItemTransactionBinding,
        val itemClick: (Transaction) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Transaction) {
            with(item) {
                binding.tvTransactionName.text = item.transactionTitle
                binding.tvTransactionAmount.text = item.transactionAmount.toString()
                binding.tvTransactionCategory.text = item.categoryName
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }


    private var items: MutableList<Transaction> = mutableListOf()

    fun setItems(items: List<Transaction>) {
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Transaction>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }
}
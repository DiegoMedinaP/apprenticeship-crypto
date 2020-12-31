package com.example.apprenticeship.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apprenticeship.R
import com.example.apprenticeship.databinding.ItemCurrencyBinding
import com.example.apprenticeship.domain.Currency

class CurrencyAdapter(private val itemClickListener: OnCurrencyClickListener) :
    ListAdapter<Currency, CurrencyAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Currency>() {

        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    interface OnCurrencyClickListener {
        fun onCurrencyClick(currency: Currency, position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCurrencyBinding.bind(view)

        fun bind(currency: Currency) {
            binding.tvCurrencyName.text = currency.comercialName
            binding.cvCurrency.setOnClickListener {
                itemClickListener.onCurrencyClick(currency, adapterPosition)
            }

            Glide.with(binding.root.context).load(CurrencyImageAdapter.getImage(currency.book))
                .centerCrop()
                .into(binding.ivCurrency)

        }
    }


}
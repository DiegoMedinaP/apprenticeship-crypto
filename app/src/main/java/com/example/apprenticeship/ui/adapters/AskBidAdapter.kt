package com.example.apprenticeship.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apprenticeship.R
import com.example.apprenticeship.databinding.ItemOrderBookBinding
import com.example.apprenticeship.domain.BidAskModel

class AskBidAdapter: ListAdapter<BidAskModel, AskBidAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<BidAskModel>() {

        override fun areItemsTheSame(oldItem: BidAskModel, newItem: BidAskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BidAskModel, newItem: BidAskModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_book, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemOrderBookBinding.bind(view)

        fun bind(bidAsk: BidAskModel) {
            binding.tvBookPriceValue.text = String.format("${bidAsk.price} mxn")
            binding.tvBookAmountValue.text = bidAsk.amount
        }
    }

}
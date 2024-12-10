package com.test.currency.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.currency.R
import com.test.currency.api.CurrencyLocal
import com.test.currency.databinding.ItemCurrencyBinding

class CurrencyAdapter(val response: List<CurrencyLocal>, val onClick: (CurrencyLocal) -> Unit) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) : ViewHolder(binding.root) {
        init {
            binding.favorites.setOnClickListener {
                val currency = response[adapterPosition]
                onClick(currency)
                currency.isFavorites = !currency.isFavorites
                binding.favorites.setImageResource(if (currency.isFavorites) R.drawable.favorites_on_yellow else R.drawable.favorites_off)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = response.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = response[position]
        holder.binding.name.text = currency.name
        holder.binding.value.text = currency.count
        holder.binding.favorites.setImageResource(if (currency.isFavorites) R.drawable.favorites_on_yellow else R.drawable.favorites_off)
    }
}
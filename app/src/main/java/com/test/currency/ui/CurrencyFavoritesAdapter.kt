package com.test.currency.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.currency.R
import com.test.currency.database.CurrencyFavorite
import com.test.currency.databinding.ItemCurrencyBinding

class CurrencyFavoritesAdapter(val items: List<CurrencyFavorite>) :
    RecyclerView.Adapter<CurrencyFavoritesAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = items[position]
        holder.binding.name.text = "${currency.name}/${currency.exchangeName}"
        holder.binding.value.text = currency.count
        holder.binding.favorites.setImageResource(R.drawable.favorites_on_yellow)
    }
}
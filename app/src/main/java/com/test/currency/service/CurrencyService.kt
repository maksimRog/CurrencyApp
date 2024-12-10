package com.test.currency.service

import com.test.currency.api.CurrencyEndpoints
import com.test.currency.api.CurrencyLocal
import com.test.currency.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CurrencyService(val retrofit: Retrofit, val database: AppDatabase) {


    fun loadSpinnerItems(
        coroutineScope: CoroutineScope,
        success: (spinnerItems: List<String>) -> Unit,
        fail: (String) -> Unit,
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                success(
                    retrofit.create(CurrencyEndpoints::class.java).loadAvailableAsync()
                        .await().symbols.keys.toList()
                )
            } catch (ex: Exception) {
                fail(ex.message?: "Error")
            }
        }
    }

    fun loadCurrencies(
        coroutineScope: CoroutineScope,
        source: String,
        success: (response: List<CurrencyLocal>) -> Unit,
        fail: (String) -> Unit,
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            try {

                val favorites = database.currencyDao().getAll()
                success(
                    retrofit.create(CurrencyEndpoints::class.java).loadCurrenciesAsync(
                        source
                    ).await().rates.map {
                        CurrencyLocal(
                            name = it.key,
                            count = it.value,
                            isFavorites = favorites.any { fav -> fav.name == source && fav.exchangeName == it.key })
                    }
                )
            } catch (ex: Exception) {
                fail(ex.message?: "Error")
            }
        }
    }
}
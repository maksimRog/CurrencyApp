package com.test.currency.service

import android.content.Context
import com.test.currency.api.CurrencyEndpoints
import com.test.currency.api.CurrencyLocal
import com.test.currency.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CurrencyService(val context: Context, val retrofit: Retrofit, val database: AppDatabase) {

    val prefs = context.getSharedPreferences("App_Prefs", Context.MODE_PRIVATE)
    var filterType
        get() = prefs.getString("Filter_Type", FilterType.A_Z.type)
        set(value) = prefs.edit().putString("Filter_Type", value).apply()

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
                fail(ex.message ?: "Error")
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
                val result = retrofit.create(CurrencyEndpoints::class.java).loadCurrenciesAsync(
                    source
                ).await().rates.map {
                    CurrencyLocal(
                        name = it.key,
                        count = it.value,
                        isFavorites = favorites.any { fav -> fav.name == source && fav.exchangeName == it.key })
                }

                success(
                    when (filterType) {
                        FilterType.Z_A.type -> result.sortedByDescending { it.name }
                        FilterType.ACS.type -> result.sortedBy { it.count.toDouble() }
                        FilterType.DESC.type -> result.sortedByDescending { it.count.toDouble() }
                        else -> result.sortedBy { it.name }
                    }
                )
            } catch (ex: Exception) {
                fail(ex.message ?: "Error")
            }
        }
    }
}
package com.test.currency.service

import com.test.currency.api.CurrencyLocal
import com.test.currency.database.AppDatabase
import com.test.currency.database.CurrencyFavorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class DatabaseService(val db: AppDatabase) {

    private val dbThread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()


    fun addFavorites(
        coroutineScope: CoroutineScope,
        currencyName: String,
        currencyLocal: CurrencyLocal
    ) {
        coroutineScope.launch(dbThread) {
            db.currencyDao().insertAll(
                CurrencyFavorite(
                    exchangeName = currencyLocal.name,
                    count = currencyLocal.count,
                    name = currencyName
                )
            )
        }
    }

    fun loadFavorites(
        coroutineScope: CoroutineScope,
        success: (List<CurrencyFavorite>) -> Unit
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            success(db.currencyDao().getAll())
        }
    }

    fun removeFavorites(
        coroutineScope: CoroutineScope,
        currencyName: String,
        currencyLocal: CurrencyLocal
    ) {
        coroutineScope.launch(dbThread) {
            db.currencyDao().delete(
                exchangeName = currencyLocal.name,
                name = currencyName
            )
        }
    }
}
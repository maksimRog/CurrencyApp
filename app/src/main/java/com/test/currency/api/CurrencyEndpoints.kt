package com.test.currency.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyEndpoints {


    @GET("exchangerates_data/latest")
    fun loadCurrenciesAsync(
        @Query("base") source: String = "USD",
        @Query("symbols") currencies: String = "",
    ): Deferred<CurrencyResponse>

    @GET("exchangerates_data/symbols")
    fun loadAvailableAsync(): Deferred<AvailableResponse>
}
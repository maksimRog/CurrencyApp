package com.test.currency.api

class CurrencyResponse(
    val rates: HashMap<String, String>,
    val timestamp: Long,
)
package com.test.currency.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyFavorite(
    @PrimaryKey (autoGenerate = true)
    val id : Int? = null ,
    val name: String?,
    val exchangeName: String?,
    val count: String?
)
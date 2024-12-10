package com.test.currency.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencyfavorite")
    fun getAll(): List<CurrencyFavorite>

    @Insert
    fun insertAll(vararg curr: CurrencyFavorite)

    @Query("Delete from currencyfavorite where name = :name and exchangeName = :exchangeName")
    fun delete(name: String, exchangeName: String)

}
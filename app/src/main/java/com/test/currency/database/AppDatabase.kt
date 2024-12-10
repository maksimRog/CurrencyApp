package com.test.currency.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyFavorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
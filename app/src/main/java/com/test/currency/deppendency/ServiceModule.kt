package com.test.currency.deppendency

import com.test.currency.database.AppDatabase
import com.test.currency.service.CurrencyService
import com.test.currency.service.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideCurrencyService(retrofit: Retrofit,database: AppDatabase) = CurrencyService(retrofit, database)

    @Provides
    fun provideDatabaseService(database: AppDatabase) = DatabaseService(database)
}
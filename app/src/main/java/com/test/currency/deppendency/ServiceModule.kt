package com.test.currency.deppendency

import android.content.Context
import com.test.currency.database.AppDatabase
import com.test.currency.service.CurrencyService
import com.test.currency.service.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideCurrencyService(
        @ApplicationContext appContext: Context,
        retrofit: Retrofit,
        database: AppDatabase
    ) = CurrencyService(appContext, retrofit, database)

    @Provides
    fun provideDatabaseService(database: AppDatabase) = DatabaseService(database)
}
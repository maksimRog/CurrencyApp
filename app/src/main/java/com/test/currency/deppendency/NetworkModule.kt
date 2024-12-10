package com.test.currency.deppendency

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.currency.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "currencies"
    ).build()

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.apilayer.com/")
        .client(OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("apikey", "A7Mv6G710hzr7rCwrzbOFdbzMlwcGZB6")
                .build()
            it.proceed(request)
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


}
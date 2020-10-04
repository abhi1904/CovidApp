package com.covidapp.di

import com.covidapp.CovidApplication
import com.covidapp.db.LocalCacheManager
import com.covidapp.services.repository.DataRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule constructor(baseURL: String) {
    var baseURL: String? = ""

    init {
        this.baseURL = baseURL
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                    builder.header(
                        "x-rapidapi-key",
                        "0a33877506mshdebf170a9ee5253p1fc2cejsnc5f5640b482b"
                    )
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {

        return GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL!!)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideDataRepository(application: CovidApplication): DataRepository {
        return DataRepository()
    }

    @Singleton
    @Provides
    fun provideLocalCache(application: CovidApplication): LocalCacheManager {
        return LocalCacheManager(application)
    }

}
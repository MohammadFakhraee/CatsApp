package com.mohammadhf.remote.di

import com.mohammadhf.remote.api.CatsApi
import com.mohammadhf.remote.interceptor.ApiKeyInterceptor
import com.mohammadhf.remote.utils.CATS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAppOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

    /**
     * We make this object a singleton in order to re-use for
     * any future API classes.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CATS_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideCatsApi(retrofit: Retrofit): CatsApi = retrofit.create(CatsApi::class.java)
}
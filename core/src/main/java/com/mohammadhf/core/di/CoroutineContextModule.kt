package com.mohammadhf.core.di

import com.mohammadhf.core.utils.CoroutineContextProvider
import com.mohammadhf.core.utils.PlatformCoroutineProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineContextModule {

    @Binds
    @Singleton
    fun bindPlatformCoroutineContext(
        platformCoroutineProvider: PlatformCoroutineProvider
    ): CoroutineContextProvider
}
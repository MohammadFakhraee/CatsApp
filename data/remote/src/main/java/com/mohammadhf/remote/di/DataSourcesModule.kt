package com.mohammadhf.remote.di

import com.mohammadhf.remote.data_source.CatsDataSource
import com.mohammadhf.remote.data_source.CatsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    @Binds
    fun bindCatsDataSource(
        catsDataSourceImpl: CatsDataSourceImpl
    ): CatsDataSource
}
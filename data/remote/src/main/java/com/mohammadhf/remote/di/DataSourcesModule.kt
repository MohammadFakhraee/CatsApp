package com.mohammadhf.remote.di

import com.mohammadhf.remote.data_source.CatsRemoteDataSource
import com.mohammadhf.remote.data_source.CatsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    @Binds
    fun bindCatsDataSource(
        catsDataSourceImpl: CatsRemoteDataSourceImpl
    ): CatsRemoteDataSource
}
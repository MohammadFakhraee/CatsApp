package com.mohammadhf.local.di

import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.data_source.breed.BreedLocalDataSourceImpl
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSource
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindBreedLocalDataSource(
        breedLocalDataSourceImpl: BreedLocalDataSourceImpl
    ): BreedLocalDataSource

    @Binds
    fun bindCatsImageLocalDataSource(
        catsImageLocalDataSourceImpl: CatsImageLocalDataSourceImpl
    ): CatsImageLocalDataSource
}
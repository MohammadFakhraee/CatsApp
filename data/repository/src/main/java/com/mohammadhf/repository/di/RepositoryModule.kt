package com.mohammadhf.repository.di

import com.mohammadhf.repository.breed.BreedRepository
import com.mohammadhf.repository.breed.BreedRepositoryImpl
import com.mohammadhf.repository.cats.CatsImageRepository
import com.mohammadhf.repository.cats.CatsImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCatsImageRepository(
        catsImageRepositoryImpl: CatsImageRepositoryImpl
    ): CatsImageRepository

    @Binds
    fun bindBreedRepository(
        breedRepositoryImpl: BreedRepositoryImpl
    ): BreedRepository
}
package com.example.repository.di

import com.example.repository.breed.BreedRepository
import com.example.repository.breed.BreedRepositoryImpl
import com.example.repository.cats.CatsImageRepository
import com.example.repository.cats.CatsImageRepositoryImpl
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
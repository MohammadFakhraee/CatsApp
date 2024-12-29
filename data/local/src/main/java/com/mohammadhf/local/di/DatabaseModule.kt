package com.mohammadhf.local.di

import android.content.Context
import androidx.room.Room
import com.mohammadhf.local.CatsAppDatabase
import com.mohammadhf.local.dao.BreedDao
import com.mohammadhf.local.dao.CatsImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): CatsAppDatabase = Room.databaseBuilder(
        context, CatsAppDatabase::class.java, "CatsApp.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideBreedDao(catsAppDatabase: CatsAppDatabase): BreedDao =
        catsAppDatabase.getBreedDao()

    @Provides
    fun provideCatsImageDao(catsAppDatabase: CatsAppDatabase): CatsImageDao =
        catsAppDatabase.getCatsImageDao()
}
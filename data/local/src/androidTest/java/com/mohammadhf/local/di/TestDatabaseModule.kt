package com.mohammadhf.local.di

import android.content.Context
import androidx.room.Room
import com.mohammadhf.local.CatsAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestDatabaseModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): CatsAppDatabase =
        Room.inMemoryDatabaseBuilder(context = context, CatsAppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}
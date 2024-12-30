package com.mohammadhf.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohammadhf.local.dao.BreedDao
import com.mohammadhf.local.dao.CatsImageDao
import com.mohammadhf.local.models.BreedLocal
import com.mohammadhf.local.models.CatsImageLocal

@Database(
    entities = [BreedLocal::class, CatsImageLocal::class],
    version = 1,
    exportSchema = false
)
abstract class CatsAppDatabase : RoomDatabase() {

    abstract fun getBreedDao(): BreedDao
    abstract fun getCatsImageDao(): CatsImageDao
}
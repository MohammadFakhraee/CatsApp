package com.mohammadhf.local.data_source.cats

import com.mohammadhf.local.dao.CatsImageDao
import com.mohammadhf.local.models.CatsImageLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatsImageLocalDataSourceImpl @Inject constructor(
    private val catsImageDao: CatsImageDao
) : CatsImageLocalDataSource {

    override suspend fun updateInsertCatsImages(catsImages: List<CatsImageLocal>) =
        catsImageDao.updateInsertCatsImages(catsImages)

    override suspend fun getCatsImages(): List<CatsImageLocal> =
        catsImageDao.getCatsImages()

    override fun streamCatsImages(): Flow<List<CatsImageLocal>> =
        catsImageDao.streamCatsImages()

    override suspend fun getCatsImagesByIds(ids: List<String>): List<CatsImageLocal> =
        catsImageDao.getCatsImagesByIds(ids)

    override suspend fun getCatsImageWithId(id: String): CatsImageLocal =
        catsImageDao.getCatsImageWithId(id)
}
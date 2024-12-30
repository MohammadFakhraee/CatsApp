package com.mohammadhf.repository.cats

import com.mohammadhf.repository.mapper.CatsImageRemoteToLocalMapper
import com.mohammadhf.local.data_source.breed.BreedLocalDataSource
import com.mohammadhf.local.data_source.cats.CatsImageLocalDataSource
import com.mohammadhf.local.models.BreedLocal
import com.mohammadhf.local.models.CatsImageLocal
import com.mohammadhf.remote.data_source.CatsRemoteDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatsImageRepositoryImpl @Inject constructor(
    private val catsImageLocalDataSource: CatsImageLocalDataSource,
    private val catsRemoteDataSource: CatsRemoteDataSource,
    private val catsImageRemoteToLocalMapper: CatsImageRemoteToLocalMapper,
    private val breedLocalDataSource: BreedLocalDataSource
) : CatsImageRepository {

    override fun streamAllCats(): Flow<List<CatsImageLocal>> =
        catsImageLocalDataSource.streamCatsImages()
            .onStart {
                val remoteCats = catsRemoteDataSource.getCatsList(0, 50)
                // Map to remote ids in order to get local equivalent
                val remoteIds = remoteCats.mapNotNull { it.id }
                // Query database to find any previously persisted data
                val existingLocalCats = catsImageLocalDataSource.getCatsImagesByIds(remoteIds)
                // Create a map of existing items to their favorite state
                val favoriteMap = existingLocalCats.associateBy({ it.catsImageId }, { it.isFavored })
                val localCats = arrayListOf<CatsImageLocal>()
                val localBreeds = arrayListOf<BreedLocal>()
                remoteCats.forEach { item ->
                    val (cat, breed) = catsImageRemoteToLocalMapper(item, favoriteMap)
                    localCats.add(cat)
                    localBreeds.add(breed)
                }

                coroutineScope {
                    launch { catsImageLocalDataSource.updateInsertCatsImages(localCats) }
                    launch { breedLocalDataSource.updateOrInsertAllBreeds(localBreeds) }
                }

            }.distinctUntilChanged()

    override suspend fun toggleCatFavorite(id: String) {
        val oldCat = catsImageLocalDataSource.getCatsImageWithId(id)
        val newCat = oldCat.copy(isFavored = !oldCat.isFavored)
        catsImageLocalDataSource.updateInsertCatsImages(arrayListOf(newCat))
    }
}
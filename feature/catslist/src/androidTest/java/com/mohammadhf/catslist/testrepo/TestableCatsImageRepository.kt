package com.mohammadhf.catslist.testrepo

import com.mohammadhf.local.models.CatsImageLocal
import com.mohammadhf.repository.cats.CatsImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestableCatsImageRepository(): CatsImageRepository {

    private val catsListStateFlow = MutableStateFlow(mockCatsImageList)

    override fun streamAllCats(): Flow<List<CatsImageLocal>> =
        catsListStateFlow

    override suspend fun toggleCatFavorite(id: String) {
        catsListStateFlow.update {
            it.map { item ->
                if (item.catsImageId == id) item.copy(isFavored = !item.isFavored)
                else item
            }
        }
    }
}
 val mockCatsImageList: List<CatsImageLocal> = arrayListOf(
    CatsImageLocal(
        catsImageId = "123",
        url = "123",
        ownedBreedId = "BREED1",
        isFavored = false
    ),
    CatsImageLocal(
        catsImageId = "456",
        url = "456",
        ownedBreedId = "BREED2",
        isFavored = false
    ),
    CatsImageLocal(
        catsImageId = "789",
        url = "789",
        ownedBreedId = "BREED3",
        isFavored = false
    )
)
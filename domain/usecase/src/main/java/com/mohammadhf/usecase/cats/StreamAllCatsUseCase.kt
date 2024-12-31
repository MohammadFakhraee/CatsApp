package com.mohammadhf.usecase.cats

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource
import com.mohammadhf.models.cats.CatsImageModel
import com.mohammadhf.repository.breed.BreedRepository
import com.mohammadhf.repository.cats.CatsImageRepository
import com.mohammadhf.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class StreamAllCatsUseCase @Inject constructor(
    private val catsImageRepository: CatsImageRepository,
    private val breedRepository: BreedRepository
) : FlowUseCase<Unit, List<CatsImageModel>>() {

    override fun invoke(param: Unit): Flow<Resource<List<CatsImageModel>, GeneralError>> =
        combine(
            catsImageRepository.streamAllCats(),
            breedRepository.streamAllBreeds()
        ) { cats, breeds ->
            val mapBreedToName = breeds.associateBy({ it.breedId }, { it.name })
            val domainModels = cats.map {
                CatsImageModel(
                    id = it.catsImageId,
                    imageUrl = it.url,
                    breedName = mapBreedToName[it.ownedBreedId] ?: "Unknown",
                    breedId = it.ownedBreedId,
                    isFavorite = it.isFavored
                )
            }
            Resource.Success(domainModels) as Resource<List<CatsImageModel>, GeneralError>
        }.catch {
            // We can create a error handler login in BaseUseCase or handle it in individual use cases.
            // But for the sake of simplicity we only throw GeneralError.UnknownError
            emit(Resource.Failure(GeneralError.UnknownError(it)))
        }
}
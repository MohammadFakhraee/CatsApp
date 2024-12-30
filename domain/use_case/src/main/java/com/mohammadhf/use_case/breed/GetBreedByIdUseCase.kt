package com.mohammadhf.use_case.breed

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource
import com.mohammadhf.models.breed.BreedModel
import com.mohammadhf.repository.breed.BreedRepository
import com.mohammadhf.use_case.base.UseCase
import javax.inject.Inject

class GetBreedByIdUseCase @Inject constructor(
    private val breedRepository: BreedRepository
) : UseCase<String, BreedModel>() {

    override suspend fun invoke(param: String): Resource<BreedModel, GeneralError> =
        try {
            val breed = breedRepository.getById(id = param)
            Resource.Success(
                BreedModel(
                    breedId = breed.breedId,
                    name = breed.name,
                    origin = breed.origin,
                    description = breed.description,
                    adaptability = breed.adaptability,
                    childFriendly = breed.childFriendly
                )
            )
        } catch (e: Exception) {
            // We can create a error handler login in BaseUseCase or handle it in individual use cases.
            // But for the sake of simplicity we only throw GeneralError.UnknownError
            Resource.Failure(GeneralError.UnknownError(e))
        }
}
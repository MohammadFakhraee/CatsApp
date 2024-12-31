package com.mohammadhf.usecase.cats

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource
import com.mohammadhf.repository.cats.CatsImageRepository
import com.mohammadhf.usecase.base.UseCase
import javax.inject.Inject

class ToggleCatFavoriteUseCase @Inject constructor(
    private val catsImageRepository: CatsImageRepository
) : UseCase<String, Unit>() {

    override suspend fun invoke(param: String): Resource<Unit, GeneralError> =
        try {
            Resource.Success(catsImageRepository.toggleCatFavorite(id = param))
        } catch (e: Exception) {
            // We can create a error handler login in BaseUseCase or handle it in individual use cases.
            // But for the sake of simplicity we only throw GeneralError.UnknownError
            Resource.Failure(GeneralError.UnknownError(e))
        }
}


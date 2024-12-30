package com.mohammadhf.use_case.base

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource

abstract class UseCase<Param, Result> : BaseUseCase {
    abstract suspend operator fun invoke(param: Param): Resource<Result, GeneralError>
}
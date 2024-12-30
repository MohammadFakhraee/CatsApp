package com.mohammadhf.use_case.base

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource
import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<Param, Result> : BaseUseCase {
    abstract operator fun invoke(param: Param): Flow<Resource<Result, GeneralError>>
}
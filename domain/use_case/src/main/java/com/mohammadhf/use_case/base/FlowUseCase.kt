package com.mohammadhf.use_case.base

import com.mohammadhf.models.base.GeneralError
import com.mohammadhf.models.base.Resource

abstract class FlowUseCase<Param, Result> : BaseUseCase {
    abstract operator fun invoke(param: Param): Resource<Result, GeneralError>
}
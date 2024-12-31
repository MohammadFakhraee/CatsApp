package com.mohammadhf.models.base

sealed interface GeneralError {
    data class ApiError(val httpException: HttpException) : GeneralError
    data object NetworkError : GeneralError
    data class UnknownError(val error: Throwable) : GeneralError
}
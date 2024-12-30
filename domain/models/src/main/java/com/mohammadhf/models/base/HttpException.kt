package com.mohammadhf.models.base

sealed interface HttpException {
    data class AccessDenied(val messageString: String? = null) : HttpException
    data class UnAuthorized(val messageString: String? = null) : HttpException
    data class IpLimit(val messageString: String? = null) : HttpException
    data class CodeInvalid(val code: Int? = null, val messageString: String? = null) : HttpException
    data class Unknown(val messageString: String? = null) : HttpException
}
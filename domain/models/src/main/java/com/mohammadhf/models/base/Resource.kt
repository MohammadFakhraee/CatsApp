package com.mohammadhf.models.base

sealed interface Resource<out S, out E> {
    data class Success<out S>(val data: S) : Resource<S, Nothing>
    data class Failure<out E>(val error: E) : Resource<Nothing, E>

    val isSuccess: Boolean
        get() = this is Success<S>

    val isFailure: Boolean
        get() = this is Failure<E>

    fun getOrNull(): S? = (this as? Success<S>)?.data

    fun errorOrNull(): E? = (this as? Failure<E>)?.error

    fun <M> map(mapTo: (S) -> M): Resource<M, E> =
        when (val r = this) {
            is Failure -> r
            is Success -> Success(mapTo(r.data))
        }
}
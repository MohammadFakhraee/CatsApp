package com.mohammadhf.core.utils

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val ui: CoroutineContext
    val io: CoroutineContext
    val bg: CoroutineContext
}
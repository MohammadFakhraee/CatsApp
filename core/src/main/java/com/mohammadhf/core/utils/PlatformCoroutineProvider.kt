package com.mohammadhf.core.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PlatformCoroutineProvider @Inject constructor() : CoroutineContextProvider {
    override val ui: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val bg: CoroutineContext = Dispatchers.Default
}
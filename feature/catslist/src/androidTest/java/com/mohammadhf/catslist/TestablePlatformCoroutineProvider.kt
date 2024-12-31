package com.mohammadhf.catslist

import com.mohammadhf.core.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestablePlatformCoroutineProvider(): CoroutineContextProvider {

    override val ui: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.Main
    override val bg: CoroutineContext = Dispatchers.Main
}
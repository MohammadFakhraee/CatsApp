package com.mohammadhf.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newReq =
            request.newBuilder()
                .addHeader("x-api-key", "live_tjch2mIz739lpBhRltSHCFXubHhct011s8K4Syb8S357bnD8IBkFZxEnzW62kgYt")

        return chain.proceed(newReq.build())
    }
}
package com.aiglepub.marvelcompose.data.network.api

import com.google.gson.internal.GsonBuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Date
import javax.inject.Inject
const val MARVEL_PUBLIC_KEY="cfa84e8f63e43679a5f9299c92a964a7"
const val MARVEL_PRIVATE_KEY="eec98a5800df793ca31812187b3ae75e91597d9a"
class QueryInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, MARVEL_PRIVATE_KEY, MARVEL_PUBLIC_KEY)

        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", MARVEL_PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}
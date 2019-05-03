package com.wembleystudios.themoviedbapp.data.api

import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(QUERY_API_KEY, apiKey).build()
        return chain.proceed(request.newBuilder().url(url).build())
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
    }
}
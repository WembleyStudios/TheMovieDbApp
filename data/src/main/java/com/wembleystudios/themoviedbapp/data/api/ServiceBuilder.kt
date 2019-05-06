package com.wembleystudios.themoviedbapp.data.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.wembleystudios.themoviedbapp.data.api.interceptor.ApiKeyInterceptor
import com.wembleystudios.themoviedbapp.data.api.interceptor.ApiRetryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
    Base Service builder used to build retrofit services
 */
class ServiceBuilder(val baseUrl: String, val apiKey: String) {

    inline fun <reified T> create(): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .addInterceptor(ApiRetryInterceptor())
            .build()
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val converterFactory = GsonConverterFactory.create(gson)
        val callAdapterFactory = RxJava2CallAdapterFactory.create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(T::class.java)
    }
}
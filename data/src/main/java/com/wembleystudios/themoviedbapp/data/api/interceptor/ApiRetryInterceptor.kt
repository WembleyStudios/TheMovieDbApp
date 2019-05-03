package com.wembleystudios.themoviedbapp.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Created by Iván Carrasco Alonso on 2019-05-03.
 */
class ApiRetryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        val delayTime = response.header(HEADER_RETRY_AFTER)?.toLong()

        delayTime?.let {
            Timer().schedule(timerTask {
                intercept(chain)
            }, delayTime)
        }

        return response
    }

    companion object {
        const val HEADER_RETRY_AFTER = "Retry-After"
    }
}
package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.model.ApiConfigurationData
import io.reactivex.Single

class ConfigurationSource(private val configurationService: ConfigurationService) {

    private var apiConfigurationData: ApiConfigurationData? = null

    fun getConfiguration(): Single<ApiConfigurationData> {
        return apiConfigurationData?.let { Single.just(it) }
            ?: configurationService.getConfiguration().doOnSuccess { apiConfigurationData = it }
    }
}
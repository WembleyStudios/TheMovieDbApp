package com.wembleystudios.themoviedbapp.data.mapper

import com.wembleystudios.themoviedbapp.data.model.ApiConfigurationData
import com.wembleystudios.themoviedbapp.data.model.MovieData

class ImageBuilder {

    fun build(movie: MovieData, apiConfigurationData: ApiConfigurationData): String {
        val baseUrl = apiConfigurationData.images?.secureBaseUrl
        val imageSize = apiConfigurationData.images?.posterSizes?.lastOrNull()
        return "$baseUrl$imageSize${movie.posterPath}"
    }
}
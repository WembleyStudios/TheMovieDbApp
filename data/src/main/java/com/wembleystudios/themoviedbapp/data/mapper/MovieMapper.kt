package com.wembleystudios.themoviedbapp.data.mapper

import com.wembleystudios.themoviedbapp.data.model.ApiConfigurationData
import com.wembleystudios.themoviedbapp.data.model.MovieData
import com.wembleystudios.themoviedbapp.domain.model.Movie

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class MovieMapper(private val imageBuilder: ImageBuilder) {

    fun map(movie: MovieData, config: ApiConfigurationData?): Movie =
        Movie(
            movie.id ?: 0,
            movie.title,
            movie.overview,
            config?.let { imageBuilder.build(movie, it) },
            movie.releaseDate
        )
}
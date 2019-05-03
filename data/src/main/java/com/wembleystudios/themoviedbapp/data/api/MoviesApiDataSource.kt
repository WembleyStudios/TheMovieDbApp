package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.mapper.MoviesPageMapper
import com.wembleystudios.themoviedbapp.data.model.ApiConfigurationData
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single
import io.reactivex.functions.BiFunction

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class MoviesApiDataSource(
    private val moviesService: MoviesService,
    private val configurationService: ConfigurationService,
    private val moviesPageMapper: MoviesPageMapper
) {

    fun getPopularMovies(page: Int): Single<MoviesPage> {
        return Single.zip(
            moviesService.getPopularMovies(page),
            configurationService.getConfiguration().onErrorReturnItem(ApiConfigurationData.EMPTY),
            BiFunction { movies, config -> moviesPageMapper.map(movies, config) })
    }


    fun getSearchMovies(query: String, page: Int): Single<MoviesPage> {
        return Single.zip(
            moviesService.getSearchMovies(query, page),
            configurationService.getConfiguration().onErrorReturnItem(ApiConfigurationData.EMPTY),
            BiFunction { movies, config -> moviesPageMapper.map(movies, config) })
    }
}
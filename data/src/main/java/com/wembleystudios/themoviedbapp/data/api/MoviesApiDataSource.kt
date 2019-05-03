package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.mapper.MoviesPageMapper
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class MoviesApiDataSource(private val moviesService: MoviesService, private val moviesPageMapper: MoviesPageMapper) {

    fun getPopularMovies(page: Int): Single<MoviesPage> =
        moviesService.getPopularMovies(page).map(moviesPageMapper::transform)

    fun getSearchMovies(query: String, page: Int): Single<MoviesPage> =
        moviesService.getSearchMovies(query, page).map(moviesPageMapper::transform)
}
package com.wembleystudios.themoviedbapp.domain.repository

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

interface MoviesRepository {

    fun getPopularMovies(page: Int): Single<MoviesPage>
    fun searchMovies(query: String, page: Int): Single<MoviesPage>
}
package com.wembleystudios.themoviedbapp.domain.repository

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

interface MoviesRepository {

    fun getPopularMovies(page: Int): Single<MoviesPage>
    fun searchMovies(search: String, page: Int): Single<MoviesPage>
}
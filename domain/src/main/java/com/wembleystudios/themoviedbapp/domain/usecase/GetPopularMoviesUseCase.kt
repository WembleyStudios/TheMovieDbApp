package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

/*
    Use case that allow get the popular movies getting the results by page
 */
interface GetPopularMoviesUseCase {

    fun getPopularMovies(page: Int): Single<MoviesPage>
}
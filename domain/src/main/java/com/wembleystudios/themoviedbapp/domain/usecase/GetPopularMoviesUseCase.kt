package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

interface GetPopularMoviesUseCase {

    fun getPopularMovies(page: Int): Single<MoviesPage>
}
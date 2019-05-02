package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

interface SearchMoviesUseCase {

    fun getPopularMovies(search: String, page: Int): Single<MoviesPage>
}
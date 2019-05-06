package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import io.reactivex.Single

/*
    Use case that allow search movies by keywords getting this information by page
 */
interface SearchMoviesUseCase {

    fun searchMovies(search: String, page: Int): Single<MoviesPage>
}
package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import io.reactivex.Single

class SearchMoviesInteractor(private val moviesRepository: MoviesRepository) : SearchMoviesUseCase {

    override fun searchMovies(search: String, page: Int): Single<MoviesPage> =
        moviesRepository.searchMovies(search, page)

}
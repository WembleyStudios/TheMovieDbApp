package com.wembleystudios.themoviedbapp.domain.usecase

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import io.reactivex.Single

/**
 * Implementation of GetPopularMoviesUseCase
 */
class GetPopularMoviesInteractor(private val moviesRepository: MoviesRepository) : GetPopularMoviesUseCase{

    override fun getPopularMovies(page: Int): Single<MoviesPage> = moviesRepository.getPopularMovies(page)
}
package com.wembleystudios.themoviedbapp.data.repository

import com.wembleystudios.themoviedbapp.data.api.MoviesApiDataSource
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(private val apiDataSource: MoviesApiDataSource) : MoviesRepository {

    override fun getPopularMovies(page: Int): Single<MoviesPage> = apiDataSource.getPopularMovies(page)


    override fun searchMovies(query: String, page: Int): Single<MoviesPage> = apiDataSource.getSearchMovies(query, page)

}
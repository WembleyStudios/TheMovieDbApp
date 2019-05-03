package com.wembleystudios.themoviedbapp.data.repository

import com.wembleystudios.themoviedbapp.data.api.ApiSource
import com.wembleystudios.themoviedbapp.data.model.mapper.MoviesPageMapper
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(private val apiSource: ApiSource) : MoviesRepository {

    override fun getPopularMovies(page: Int, language: String): Single<MoviesPage> =
        apiSource.getPopularMovies(page, language).map { MoviesPageMapper().transform(it) }


    override fun searchMovies(query: String, page: Int, language: String): Single<MoviesPage> =
        apiSource.getSearchMovies(query, page, language).map { MoviesPageMapper().transform(it) }

}
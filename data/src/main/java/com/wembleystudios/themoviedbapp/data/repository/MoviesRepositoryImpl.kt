package com.wembleystudios.themoviedbapp.data.repository

import com.wembleystudios.themoviedbapp.data.api.ApiSource
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(val apiSource: ApiSource) : MoviesRepository {

    override fun getPopularMovies(page: Int): Single<MoviesPage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMovies(search: String, page: Int): Single<MoviesPage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
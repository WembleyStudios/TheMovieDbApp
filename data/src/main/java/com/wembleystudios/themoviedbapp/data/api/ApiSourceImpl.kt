package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import io.reactivex.Observable

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class ApiSourceImpl(val moviesService: MoviesService) : ApiSource {
    override fun getPopularMovies(page: Int, language: String): Observable<MoviesPageData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSearchMovies(query: String, page: Int, language: String): Observable<MoviesPageData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
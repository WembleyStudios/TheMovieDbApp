package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import io.reactivex.Single


/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
interface ApiSource {

    fun getPopularMovies(page: Int, language: String): Single<MoviesPageData>

    fun getSearchMovies(query: String, page: Int, language: String): Single<MoviesPageData>
}
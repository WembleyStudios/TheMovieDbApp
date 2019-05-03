package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MoviesPageData>

    @GET("3/search/movie")
    fun getSearchMovies(@Query("query") query: String, @Query("page") page: Int): Single<MoviesPageData>
}
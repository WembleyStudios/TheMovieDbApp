package com.wembleystudios.themoviedbapp.data.api

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<MoviesPageData>

    @GET("/search/movie")
    fun getSearchMovies(
        @Query("query") query: String, @Query("page") page: Int,
        @Query("language") language: String
    ): Single<MoviesPageData>
}
package com.wembleystudios.themoviedbapp.data.model

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
data class MovieData(
    val vote_count: Int?,
    val id: Int?,
    val video: Boolean?,
    val vote_average: Float?,
    val title: String?,
    val popularity: Float?,
    val poster_path: String?,
    val original_language: String?,
    val genre_ids: List<Int>?,
    val backdrop_path: String?,
    val adult: Boolean?,
    val overview: String?,
    val releaseDate: String?
)

package com.wembleystudios.themoviedbapp.domain.model


data class MoviesPage(
    val page: Int,
    val results: List<Movie>,
    val hasMoreResults: Boolean
)
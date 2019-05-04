package com.wembleystudios.themoviedbapp.presentation.model

data class MoviesPagePresentation(
    val page: Int,
    val results: List<MoviePresentation>,
    val hasMoreResults: Boolean
)
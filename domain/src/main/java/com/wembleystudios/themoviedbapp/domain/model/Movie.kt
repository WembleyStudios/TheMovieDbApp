package com.wembleystudios.themoviedbapp.domain.model

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val image: String?
)
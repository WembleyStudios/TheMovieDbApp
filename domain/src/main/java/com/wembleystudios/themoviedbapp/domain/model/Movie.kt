package com.wembleystudios.themoviedbapp.domain.model

import java.util.*

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val image: String?,
    val releaseDate: Date?
)
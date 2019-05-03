package com.wembleystudios.themoviedbapp.data.model

import java.util.*

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
data class MovieData(
    val id: Int?,
    val video: Boolean?,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: Date?
)

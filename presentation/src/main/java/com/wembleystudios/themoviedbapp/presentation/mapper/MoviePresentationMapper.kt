package com.wembleystudios.themoviedbapp.presentation.mapper

import com.wembleystudios.themoviedbapp.domain.model.Movie
import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation
import java.text.SimpleDateFormat
import java.util.*

class MoviePresentationMapper {

    fun map(from: Movie): MoviePresentation = MoviePresentation(
        from.id.toString(),
        from.title.orEmpty(),
        from.overview.orEmpty(),
        from.releaseDate?.parseYear.orEmpty(),
        from.image.orEmpty()
    )


    private val Date.parseYear: String
        get() = SimpleDateFormat("yyyy", Locale.ROOT).format(this)
}
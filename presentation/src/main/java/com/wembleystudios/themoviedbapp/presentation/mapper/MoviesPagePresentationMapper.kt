package com.wembleystudios.themoviedbapp.presentation.mapper

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.presentation.model.MoviesPagePresentation

class MoviesPagePresentationMapper(private val moviePresentationMapper: MoviePresentationMapper) {

    fun map(from: MoviesPage): MoviesPagePresentation =
        MoviesPagePresentation(
            from.page,
            from.results.map(moviePresentationMapper::map),
            from.hasMoreResults
        )
}
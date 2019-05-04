package com.wembleystudios.themoviedbapp.presentation.mapper

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.presentation.model.MoviesPageSearchPresentation

class MoviesPagePresentationMapper(private val moviePresentationMapper: MoviePresentationMapper) {

    fun map(from: MoviesPage, search: String): MoviesPageSearchPresentation =
        MoviesPageSearchPresentation(
            from.page,
            from.results.map(moviePresentationMapper::map),
            from.hasMoreResults,
            search
        )
}
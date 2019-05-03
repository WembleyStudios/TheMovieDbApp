package com.wembleystudios.themoviedbapp.presentation.mapper

import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation

class MoviesPagePresentationMapper(private val moviePresentationMapper: MoviePresentationMapper) {

    fun map(from: MoviesPage): List<MoviePresentation> = from.results.map(moviePresentationMapper::map)
}
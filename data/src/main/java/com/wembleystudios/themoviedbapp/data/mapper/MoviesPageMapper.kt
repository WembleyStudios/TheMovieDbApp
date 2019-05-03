package com.wembleystudios.themoviedbapp.data.mapper

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.repository.Mapper

/**
 * Created by Iván Carrasco Alonso on 2019-05-03.
 */
class MoviesPageMapper(private val movieMapper: MovieMapper) : Mapper<MoviesPageData, MoviesPage> {
    override fun transform(input: MoviesPageData): MoviesPage {
        return MoviesPage(
            input.page ?: 0,
            input.results?.map(movieMapper::transform) ?: emptyList(),
            (input.page ?: 0) < (input.totalPages ?: 0)
        )
    }
}
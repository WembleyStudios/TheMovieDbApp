package com.wembleystudios.themoviedbapp.data.model.mapper

import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage

/**
 * Created by Iván Carrasco Alonso on 2019-05-03.
 */
class MoviesPageMapper : Mapper<MoviesPageData, MoviesPage> {
    override fun transform(input: MoviesPageData): MoviesPage {
        return MoviesPage(
            input.page,
            input.results.map { MovieMapper().transform(it) },
            input.page < input.total_pages
        )
    }
}
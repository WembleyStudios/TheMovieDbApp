package com.wembleystudios.themoviedbapp.data.mapper

import com.wembleystudios.themoviedbapp.data.model.ApiConfigurationData
import com.wembleystudios.themoviedbapp.data.model.MoviesPageData
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage

/**
 * Created by Iván Carrasco Alonso on 2019-05-03.
 */
class MoviesPageMapper(private val movieMapper: MovieMapper) {

    fun map(page: MoviesPageData, config: ApiConfigurationData?): MoviesPage {
        return MoviesPage(
            page.page ?: 0,
            page.results?.map { movieMapper.map(it, config) } ?: emptyList(),
            (page.page ?: 0) < (page.totalPages ?: 0)
        )
    }
}
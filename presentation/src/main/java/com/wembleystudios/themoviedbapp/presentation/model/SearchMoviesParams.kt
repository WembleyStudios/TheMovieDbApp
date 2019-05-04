package com.wembleystudios.themoviedbapp.presentation.model

import com.wembleystudios.themoviedbapp.presentation.viewmodel.Constants

data class SearchMoviesParams(val search: String, val page: Int) {

    companion object {
        val DEFAULT = SearchMoviesParams("", Constants.FIRST_PAGE)
    }
}
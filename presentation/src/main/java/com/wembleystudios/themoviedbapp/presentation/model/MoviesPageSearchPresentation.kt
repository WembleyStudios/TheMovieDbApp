package com.wembleystudios.themoviedbapp.presentation.model

data class MoviesPageSearchPresentation(
    val page: Int,
    val results: List<MoviePresentation>,
    val hasMoreResults: Boolean,
    val search : String
){

    companion object{
        val EMPTY = MoviesPageSearchPresentation(0, emptyList(), false, "")
    }
}
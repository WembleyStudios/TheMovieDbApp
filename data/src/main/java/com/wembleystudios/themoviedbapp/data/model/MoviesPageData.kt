package com.wembleystudios.themoviedbapp.data.model

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
data class MoviesPageData(val page: Int, val total_results: Int, val total_pages: Int, val results: List<MovieData>)
package com.wembleystudios.themoviedbapp.presentation.viewmodel

import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation

data class MainState(val movies: List<MoviePresentation>, val isError: Boolean, val isLoading: Boolean)
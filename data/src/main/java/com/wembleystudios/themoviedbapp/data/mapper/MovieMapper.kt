package com.wembleystudios.themoviedbapp.data.mapper

import com.wembleystudios.themoviedbapp.data.model.MovieData
import com.wembleystudios.themoviedbapp.domain.model.Movie
import com.wembleystudios.themoviedbapp.domain.repository.Mapper

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class MovieMapper : Mapper<MovieData, Movie> {
    override fun transform(input: MovieData): Movie =
        Movie(input.id ?: 0, input.title, input.overview, input.posterPath, input.releaseDate)
}
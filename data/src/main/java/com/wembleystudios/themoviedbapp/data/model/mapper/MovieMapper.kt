package com.wembleystudios.themoviedbapp.data.model.mapper

import com.wembleystudios.themoviedbapp.data.model.MovieData
import com.wembleystudios.themoviedbapp.domain.model.Movie

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
class MovieMapper : Mapper<MovieData, Movie> {
    override fun transform(input: MovieData): Movie =
        Movie(input.id, input.title, input.overview, input.releaseDate, input.poster_path)
}
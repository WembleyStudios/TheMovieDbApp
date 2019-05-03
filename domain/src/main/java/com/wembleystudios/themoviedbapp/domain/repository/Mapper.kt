package com.wembleystudios.themoviedbapp.domain.repository

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
interface Mapper<in I, out O> {
    fun transform(input: I): O
}
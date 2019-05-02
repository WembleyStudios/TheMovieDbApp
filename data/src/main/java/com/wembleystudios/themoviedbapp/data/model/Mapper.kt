package com.wembleystudios.themoviedbapp.data.model

/**
 * Created by Iván Carrasco Alonso on 02/05/2019.
 */
interface Mapper<in I, out O> {
    fun transform(input: I): O
}
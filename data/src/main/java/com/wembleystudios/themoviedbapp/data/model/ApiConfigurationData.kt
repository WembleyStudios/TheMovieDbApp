package com.wembleystudios.themoviedbapp.data.model

data class ApiConfigurationData(val images: ImagesConfiguration?){

    companion object{
        val EMPTY = ApiConfigurationData(null)
    }
}
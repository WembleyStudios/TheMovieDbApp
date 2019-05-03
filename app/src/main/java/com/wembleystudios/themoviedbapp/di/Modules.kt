package com.wembleystudios.themoviedbapp.di

import com.wembleystudios.themoviedbapp.data.api.ConfigurationService
import com.wembleystudios.themoviedbapp.data.api.MoviesApiDataSource
import com.wembleystudios.themoviedbapp.data.api.MoviesService
import com.wembleystudios.themoviedbapp.data.api.ServiceBuilder
import com.wembleystudios.themoviedbapp.data.mapper.ImageBuilder
import com.wembleystudios.themoviedbapp.data.mapper.MovieMapper
import com.wembleystudios.themoviedbapp.data.mapper.MoviesPageMapper
import com.wembleystudios.themoviedbapp.data.repository.MoviesRepositoryImpl
import com.wembleystudios.themoviedbapp.domain.repository.MoviesRepository
import org.koin.dsl.module.module


const val PROPERTY_API_KEY = "api_key"
const val PROPERTY_BASE_URL = "base_url"

val appModule = module {

}

val presentationModule = module {

}

val domainModule = module {

}

val dataModule = module {
    single { ServiceBuilder(getProperty(PROPERTY_BASE_URL), getProperty(PROPERTY_API_KEY)) }
    single<ConfigurationService> { get<ServiceBuilder>().create() }
    factory<MoviesService> { get<ServiceBuilder>().create() }
    factory { ImageBuilder() }
    factory { MovieMapper(get()) }
    factory { MoviesPageMapper(get()) }
    factory { MoviesApiDataSource(get(), get(), get()) }
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
}
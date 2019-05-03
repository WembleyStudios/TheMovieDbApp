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
import com.wembleystudios.themoviedbapp.domain.usecase.GetPopularMoviesInteractor
import com.wembleystudios.themoviedbapp.domain.usecase.GetPopularMoviesUseCase
import com.wembleystudios.themoviedbapp.domain.usecase.SearchMoviesInteractor
import com.wembleystudios.themoviedbapp.domain.usecase.SearchMoviesUseCase
import com.wembleystudios.themoviedbapp.presentation.mapper.MoviePresentationMapper
import com.wembleystudios.themoviedbapp.presentation.mapper.MoviesPagePresentationMapper
import com.wembleystudios.themoviedbapp.presentation.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


const val PROPERTY_API_KEY = "api_key"
const val PROPERTY_BASE_URL = "base_url"

private const val OBSERVE_SCHEDULER = "observe_scheduler"
private const val SUBSCRIBE_SCHEDULER = "subscribe_scheduler"

val appModule = module {
    factory(OBSERVE_SCHEDULER) { AndroidSchedulers.mainThread() }
    factory(SUBSCRIBE_SCHEDULER) { Schedulers.io() }
}

val presentationModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(SUBSCRIBE_SCHEDULER), get(OBSERVE_SCHEDULER)) }
    factory { MoviePresentationMapper() }
    factory { MoviesPagePresentationMapper(get()) }
}

val domainModule = module {
    factory<GetPopularMoviesUseCase> { GetPopularMoviesInteractor(get()) }
    factory<SearchMoviesUseCase> { SearchMoviesInteractor(get()) }
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
package com.wembleystudios.themoviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wembleystudios.themoviedbapp.domain.usecase.GetPopularMoviesUseCase
import com.wembleystudios.themoviedbapp.domain.usecase.SearchMoviesUseCase
import com.wembleystudios.themoviedbapp.presentation.mapper.MoviesPagePresentationMapper
import com.wembleystudios.themoviedbapp.presentation.model.MoviesPagePresentation
import com.wembleystudios.themoviedbapp.presentation.model.SearchMoviesParams
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesPageMapper: MoviesPagePresentationMapper,
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler
) : ViewModel() {

    private val disposableBag: CompositeDisposable = CompositeDisposable()

    private var lastResults: MoviesPagePresentation? = null

    private val _stateLiveData: MutableLiveData<MainState> = MutableLiveData()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    private val searchParamsSubject: BehaviorSubject<SearchMoviesParams> =
        BehaviorSubject.createDefault(SearchMoviesParams.DEFAULT)

    init {
        val disposable =
            searchParamsSubject.switchMapSingle { params ->
                /*Get different use case based on params*/
                val useCase = if (params.search.isBlank()) {
                    getPopularMoviesUseCase.getPopularMovies(params.page)
                } else {
                    searchMoviesUseCase.searchMovies(params.search, params.page)
                }
                /*Save the last result every time and notify UI of loading state on every new subscription*/
                useCase.map(moviesPageMapper::map)
                    .map { new ->
                        /*Append new loaded movies to the last results*/
                        val last = lastResults?.results ?: emptyList()
                        new.copy(results = last + new.results)
                    }
                    .doOnSuccess { lastResults = it }
                    .map(MoviesPagePresentation::results)
                    .onErrorReturnItem(emptyList())
                    .subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)
            }.subscribe({ results ->
                _stateLiveData.value = MainState(movies = results, isError = false, isLoading = false)
            }, {
                _stateLiveData.value = MainState(movies = emptyList(), isError = true, isLoading = false)
            })


        disposableBag.add(disposable)
    }

    fun onSearch(search: String) {
        /*Reset last results on every new search*/
        lastResults = null
        val lastState = _stateLiveData.value
        _stateLiveData.postValue(lastState?.copy(isError = false, isLoading = true))
        searchParamsSubject.onNext(SearchMoviesParams(search, FIRST_PAGE))
    }

    fun loadMore() {
        if (lastResults?.hasMoreResults == true) {
            val lastState = _stateLiveData.value
            _stateLiveData.postValue(lastState?.copy(isError = false, isLoading = true))
            val lastParams = searchParamsSubject.value ?: SearchMoviesParams.DEFAULT
            searchParamsSubject.onNext(lastParams.copy(page = lastParams.page + 1))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.dispose()
    }

    companion object {
        private const val FIRST_PAGE = 1
    }

}
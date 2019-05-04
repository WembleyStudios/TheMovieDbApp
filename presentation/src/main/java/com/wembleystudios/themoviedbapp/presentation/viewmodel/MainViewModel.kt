package com.wembleystudios.themoviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.usecase.GetPopularMoviesUseCase
import com.wembleystudios.themoviedbapp.domain.usecase.SearchMoviesUseCase
import com.wembleystudios.themoviedbapp.presentation.mapper.MoviesPagePresentationMapper
import com.wembleystudios.themoviedbapp.presentation.model.MoviesPageSearchPresentation
import com.wembleystudios.themoviedbapp.presentation.model.SearchMoviesParams
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.Delegates

class MainViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesPageMapper: MoviesPagePresentationMapper,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : ViewModel() {

    private val disposableBag: CompositeDisposable = CompositeDisposable()

    private val pageObservable: BehaviorSubject<Int> = BehaviorSubject.createDefault(FIRST_PAGE)

    private var lastResults: MoviesPage? = null

    var searchObservable: Observable<String> by Delegates.observable(Observable.just("")) { _, _, _ ->
        subscribeMovies()
    }

    private val _stateLiveData: MutableLiveData<MainState> = MutableLiveData()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    init {
        subscribeMovies()
    }

    private fun subscribeMovies() {
        disposableBag.clear()
        disposableBag.add(Observable.combineLatest<String, Int, SearchMoviesParams>(
            searchObservable.doOnNext { pageObservable.onNext(FIRST_PAGE) },
            pageObservable,
            BiFunction { search, page -> SearchMoviesParams(search, page) }
        ).switchMapSingle { params ->
            val useCase = if (params.search.isBlank()) {
                getPopularMoviesUseCase.getPopularMovies(params.page)
            } else {
                searchMoviesUseCase.searchMovies(params.search, params.page)
            }
            useCase
                .doOnSuccess { lastResults = it }
                .doOnSubscribe {
                    val lastState = _stateLiveData.value
                    _stateLiveData.postValue(lastState?.copy(isError = false, isLoading = true))
                }
                .map { moviesPageMapper.map(it, params.search) }
                .onErrorReturnItem(MoviesPageSearchPresentation.EMPTY)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
        }.scan { old: MoviesPageSearchPresentation, new: MoviesPageSearchPresentation ->
            if (old.search == new.search && old.page != new.page) {
                new.copy(results = old.results + new.results)
            } else {
                new
            }
        }.subscribe(
            { page ->
                _stateLiveData.value = MainState(movies = page.results, isError = false, isLoading = false)
            }, {
                _stateLiveData.value = MainState(movies = emptyList(), isError = true, isLoading = false)
            })
        )
    }

    fun loadMore() {
        if (lastResults?.hasMoreResults == true) {
            lastResults?.page?.let { page -> pageObservable.onNext(page + 1) }
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
package com.wembleystudios.themoviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wembleystudios.themoviedbapp.domain.model.MoviesPage
import com.wembleystudios.themoviedbapp.domain.usecase.GetPopularMoviesUseCase
import com.wembleystudios.themoviedbapp.domain.usecase.SearchMoviesUseCase
import com.wembleystudios.themoviedbapp.presentation.mapper.MoviesPagePresentationMapper
import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation
import com.wembleystudios.themoviedbapp.presentation.model.SearchMoviesParams
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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

    private var searchDisposable: Disposable? = null

    private var pageObservable: BehaviorSubject<Int> = BehaviorSubject.createDefault(FIRST_PAGE)

    private var lastResults: MoviesPage? = null

    var searchObservable: Observable<String> by Delegates.observable(Observable.empty()) { _, _, searchObservable ->
        searchDisposable?.dispose()
        searchDisposable = Observable.combineLatest<String, Int, SearchMoviesParams>(
            searchObservable.doOnNext { pageObservable.onNext(FIRST_PAGE) },
            pageObservable,
            BiFunction { search, page -> SearchMoviesParams(search, page) }
        ).switchMapSingle { params ->
            searchMoviesUseCase.searchMovies(params.search, params.page)
                .doOnSuccess { lastResults = it }
                .map(moviesPageMapper::map)
                .onErrorReturnItem(emptyList())
        }.subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(
                { items ->
                    _moviesLiveData.value = items
                },
                {
                    _moviesLiveData.value = emptyList()
                })

        searchDisposable?.let(disposableBag::add)
    }

    private val _moviesLiveData: MutableLiveData<List<MoviePresentation>> = MutableLiveData()
    val moviesLiveData: LiveData<List<MoviePresentation>> = _moviesLiveData

    fun loadMore() {
        if (lastResults?.hasMoreResults == true) {
            pageObservable.onNext(lastResults?.page ?: FIRST_PAGE)
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
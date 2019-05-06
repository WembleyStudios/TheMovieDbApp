package com.wembleystudios.themoviedbapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wembleystudios.themoviedbapp.adapter.EndlessScrollListener
import com.wembleystudios.themoviedbapp.adapter.PagedMoviesAdapter
import com.wembleystudios.themoviedbapp.presentation.viewmodel.MainViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/*
    Main activity of the app and the only interaction point for the user
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagedMoviesAdapter()
        val layoutManager = LinearLayoutManager(this)
        rvMovies.layoutManager = layoutManager
        rvMovies.adapter = adapter

        /*
           Scroll listener implementation for load the next page when the user scroll down the recycler view
         */
        rvMovies.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) = viewModel.loadMore()
        })

        /*
            Disposable definition for dispose when the activity is in onDestroy lifecycle
            Use the RxTextView observable of this way:
                1. Skip the initial value because is empty and useless
                2. Define a time space between the observables for not saturate the server and avoid the 429 error
                3. Map the observables values to String removing the white spaces
                4. Use distindsdf. operator to avoid observing repeated values
                5. Subscribe the consumer function
         */
        disposable = RxTextView.textChanges(etSearch)
            .skipInitialValue()
            .debounce(250, TimeUnit.MILLISECONDS)
            .map { it.toString().trim() }
            .distinctUntilChanged()
            .subscribe(viewModel::onSearch)


        /*
            StateLiveData is observed and the UI change attend state of this object:
            Two cases:
                1. Loading state: UI show the progress bar
                2. Error state: UI show the error text view
            The adapter is notify ever with a empty list in the last cases and with a not empty list
            in a correct response

         */
        viewModel.stateLiveData.observe(this, Observer {
            it?.let { state ->
                viewLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                tvError.visibility = if (state.isError) View.VISIBLE else View.GONE
                adapter.submitList(state.movies)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}

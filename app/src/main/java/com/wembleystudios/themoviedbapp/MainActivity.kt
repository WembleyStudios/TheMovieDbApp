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
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagedMoviesAdapter()
        val layoutManager = LinearLayoutManager(this)
        rvMovies.layoutManager = layoutManager
        rvMovies.adapter = adapter
        rvMovies.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) = viewModel.loadMore()
        })

        viewModel.searchObservable =
            RxTextView.textChanges(etSearch)
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .distinctUntilChanged()

        viewModel.stateLiveData.observe(this, Observer {
            it?.let { state ->
                viewLoading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                tvError.visibility = if (state.isError) View.VISIBLE else View.GONE
                adapter.submitList(state.movies)
            }
        })
    }
}

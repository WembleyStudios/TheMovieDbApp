package com.wembleystudios.themoviedbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wembleystudios.themoviedbapp.adapter.PagedMoviesAdapter
import com.wembleystudios.themoviedbapp.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val adapter: PagedMoviesAdapter by inject()
    private val layoutManager: LinearLayoutManager
            by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies.apply {
            setHasFixedSize(true)
            layoutManager = this@MainActivity.layoutManager
            adapter = this@MainActivity.adapter
        }

        viewModel.searchObservable =
            RxTextView.textChanges(etSearch)
                .skipInitialValue()
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { it.toString() }

        viewModel.moviesLiveData.observe(this, Observer {
            adapter.onItemsUpdate(it)
        })
    }
}

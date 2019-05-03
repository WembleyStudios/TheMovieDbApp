package com.wembleystudios.themoviedbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import com.wembleystudios.themoviedbapp.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.searchObservable =
            RxTextView.textChanges(etSearch)
                .skipInitialValue()
                .debounce(250, TimeUnit.MILLISECONDS)
                .map { it.toString() }
    }
}

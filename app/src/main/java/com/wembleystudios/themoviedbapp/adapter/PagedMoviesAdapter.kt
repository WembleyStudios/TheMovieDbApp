package com.wembleystudios.themoviedbapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wembleystudios.themoviedbapp.R
import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Iván Carrasco Alonso on 04/05/2019.
 */
class PagedMoviesAdapter :
    ListAdapter<MoviePresentation, PagedMoviesAdapter.PagedMoviesViewHolder>(DefaultItemCallback(MoviePresentation::id)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagedMoviesViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: PagedMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        Glide.with(holder.itemView.context).load(movie.image).into(holder.ivPicture)
        holder.tvOverview.text = movie.overview
        holder.tvTitle.text = movie.title
        holder.tvReleaseDate.text = movie.releaseDate
    }

    inner class PagedMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPicture: ImageView = view.ivPicture
        val tvTitle: TextView = view.tvTitle
        val tvOverview: TextView = view.tvOverview
        val tvReleaseDate: TextView = view.tvReleaseDate
    }
}
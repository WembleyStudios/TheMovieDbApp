package com.wembleystudios.themoviedbapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wembleystudios.themoviedbapp.R
import com.wembleystudios.themoviedbapp.presentation.model.MoviePresentation
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Iván Carrasco Alonso on 04/05/2019.
 */
class PagedMoviesAdapter(private val context: Context) :
    RecyclerView.Adapter<PagedMoviesAdapter.PagedMoviesViewHolder>() {

    private val data = mutableListOf<MoviePresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PagedMoviesViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = this.data.size

    override fun onBindViewHolder(holder: PagedMoviesViewHolder, position: Int) {
        Glide.with(context).load(data[position].image).into(holder.ivPicture)
        holder.tvOverview.text = data[position].overview
        holder.tvTitle.text = data[position].title
        holder.tvReleaseDate.text = data[position].releaseDate
    }

    fun onItemsUpdate(newData: List<MoviePresentation>) {
        this.data.clear()
        this.data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class PagedMoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val ivPicture: ImageView = view.ivPicture
        val tvTitle: TextView = view.tvTitle
        val tvOverview: TextView = view.tvOverview
        val tvReleaseDate: TextView = view.tvReleaseDate
    }
}
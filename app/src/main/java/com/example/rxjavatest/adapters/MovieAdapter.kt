package com.example.rxjavatest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rxjavatest.constants.Constants
import com.example.rxjavatest.R
import com.example.rxjavatest.models.MoviesResponse
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(var moviesResponse: MoviesResponse, var context: Context) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false))
    }

    override fun getItemCount(): Int {
        return if(moviesResponse.results.isEmpty()) 1 else moviesResponse.results.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fullImageUrl: String = Constants.URL.plus(moviesResponse.results[position].movieImageUrl)

        holder.titleTxt.text = moviesResponse.results[position].movieTitle
        holder.overviewTxt.text = moviesResponse.results[position].movieOverview

        Glide.with(context)
            .load(fullImageUrl)
            .placeholder(R.drawable.ic_placeholder_48dp)
            .error(R.drawable.ic_error_image_48dp)
            .skipMemoryCache(true)
            .centerCrop()
            .into(holder.imageVw)

    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var titleTxt: TextView = view.title
        var imageVw: ImageView = view.movie_image
        var overviewTxt: TextView = view.movie_overview
    }
}
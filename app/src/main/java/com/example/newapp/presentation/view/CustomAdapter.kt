package com.example.newapp.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newapp.R
import com.example.newapp.model.Movie

class CustomAdapter(private val context: Context, private val itemsList: List<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = itemsList[position]

        Glide.with(context)
            .load(IMAGE_PREFIX + itemsViewModel.backdrop_path)
            .into(holder.imageView)
        holder.textView.text = itemsViewModel.title
    }

    override fun getItemCount() = itemsList.count()

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.movieImage)
        val textView: TextView = itemView.findViewById(R.id.movieName)
    }

    companion object {
        const val IMAGE_PREFIX = "https://image.tmdb.org/t/p/w500"
    }
}
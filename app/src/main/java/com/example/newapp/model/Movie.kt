package com.example.newapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
) : Parcelable

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int
)
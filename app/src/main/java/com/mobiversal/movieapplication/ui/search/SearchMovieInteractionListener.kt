package com.mobiversal.movieapplication.ui.search

import com.mobiversal.movieapplication.movie.Movie

interface SearchMovieInteractionListener {
    fun updateMovie(movie: Movie)
}
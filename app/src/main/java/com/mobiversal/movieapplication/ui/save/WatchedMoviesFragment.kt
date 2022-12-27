package com.mobiversal.movieapplication.ui.save

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.movie.Movie
import com.mobiversal.movieapplication.movie.MoviesRepository
import com.mobiversal.movieapplication.ui.search.MoviesAdapter
import com.mobiversal.movieapplication.ui.search.SearchMovieInteractionListener
import kotlinx.android.synthetic.main.fragment_saved_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchedMoviesFragment : Fragment(), SearchMovieInteractionListener {

    private val movieRepository = MoviesRepository.instance
    private var adapter: MoviesAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getMovies()
        return inflater.inflate(R.layout.fragment_saved_movies, container, false)
    }

    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getWatchedMovies()
            withContext(Dispatchers.Main) {
                setupRecyclerView(movies)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovies()
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        recyclerview_favorite.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.adapter = MoviesAdapter(movies, this)
        recyclerview_favorite.adapter = adapter
    }

    override fun updateMovie(movie: Movie) {
        GlobalScope.launch{
            movieRepository.save(movie)
            withContext(Dispatchers.Main){
                adapter?.notifyDataSetChanged()
            }
        }
    }
}
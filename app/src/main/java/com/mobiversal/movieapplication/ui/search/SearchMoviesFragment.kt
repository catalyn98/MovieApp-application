package com.mobiversal.movieapplication.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.MainActivity
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.actor.ActorsRepository
import com.mobiversal.movieapplication.actor.FavouriteActor
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreRepository
import com.mobiversal.movieapplication.movie.Movie
import com.mobiversal.movieapplication.movie.MoviesRepository
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMoviesFragment : Fragment(), SearchMovieInteractionListener {

    companion object {
        private val TAG = SearchMoviesFragment::class.java.simpleName
    }

    private val movieRepository = MoviesRepository.instance
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorsRepository.instance
    private var selectedGenres: List<Genre> = emptyList()
    private var selectedActors: List<FavouriteActor> = emptyList()
    private var hasActors = false
    private var hasGenres = false
    private var adapter: MoviesAdapter?= null
    private var searchQuery: String? = null

    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            getSelectedActors()
            getSelectedGenres()
        }
    }

    private fun getSelectedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedGenres = genreRepository.getAll()
            hasGenres = true
            checkRequestAndSend()
        }
    }

    private fun getSelectedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedActors = actorRepository.getAll()
            hasActors = true
            checkRequestAndSend()
        }
    }

    private fun checkRequestAndSend() {
        if (hasActors && hasGenres) {
            GlobalScope.launch(Dispatchers.IO) {
                val movies = movieRepository.getAllRemote(
                    convertActorListToString(selectedActors),
                    convertGenreListToString(selectedGenres)
                )
                withContext(Dispatchers.Main) {
                    onRemoteMoviesReady(movies)
                }
            }
        }
    }

    fun search(query: String) {
        Log.d(TAG, "Search query is: $query")

        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieRepository.searchMovies(query)
            withContext(Dispatchers.Main) {
                onRemoteMoviesReady(movies)
            }
        }
    }

    private fun convertGenreListToString(genreList: List<Genre>): String {
        val selectedGenresIds: MutableList<Int> = mutableListOf()
        for (genre in genreList) {
            selectedGenresIds.add(genre.id)
        }
        return selectedGenresIds.joinToString("|")
    }

    private fun convertActorListToString(actorList: List<FavouriteActor>): String {
        val selectedActorIds: MutableList<Int> = mutableListOf()
        for (actor in actorList) {
            selectedActorIds.add(actor.id)
        }
        return selectedActorIds.joinToString("|")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferencesButton: Button = view.findViewById(R.id.button_preferences_from_movie_list)
        preferencesButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Called once we get the movies from the remote repository
    private fun onRemoteMoviesReady(movies: List<Movie>) {
        // Get all movies locally
        GlobalScope.launch {
            val savedMovies = movieRepository.getAll()
            // Map the movies from the remote repository to the new list
            val newMoviesList = movies.map {  movie ->
                /*
                 * Create the new isFavorite and isWatched flags by the saved entities
                 */
                val isFavorite = savedMovies.find { it.id == movie.id && (it.isFavorite ?: false) } != null
                val isWatched = savedMovies.find { it.id == movie.id && (it.isWatched ?: false) } != null

                // Create the new movie entity
                movie.copy(
                    isFavorite = isFavorite,
                    isWatched = isWatched
                )
            }

            // Once we mapped all the remote response, create the recycler view
            withContext(Dispatchers.Main) {
                setupRecyclerView(newMoviesList)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (searchQuery.isNullOrBlank()) {
            getMovies()
        } else {
            search(searchQuery!!)
        }
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        iv_movie_saved.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.adapter = MoviesAdapter(movies, this)
        iv_movie_saved.adapter = adapter
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
package com.mobiversal.movieapplication.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.movie.video.Video
import com.mobiversal.movieapplication.network.Constants.KEY_MOVIE_ID
import com.mobiversal.movieapplication.network.Constants.POSTER_URL
import com.mobiversal.movieapplication.utils.ImageLoader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        private val TAG = MovieDetailsActivity::class.java.simpleName
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance
    private var movie: Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        intent?.extras?.let { bundle ->
            val movieId = bundle.getInt(KEY_MOVIE_ID, -1)
            if (movieId == -1) error("Invalid movie id")
            Log.d(TAG, "Movie id is: $movieId")
            fetchMovieDetails(movieId)
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        GlobalScope.launch {
            val movie: Movie = moviesRepository.getMoviesDetails(movieId)
            this@MovieDetailsActivity.movie = movie
            withContext(Dispatchers.Main) {
                onRemoteMovieDetailsReady(movie)
            }
        }
    }
    private fun onRemoteMovieDetailsReady(movie: Movie) {
        Log.d(TAG, "Movie = $movie")
        populatePoster(movie)
        populateMovieDetails(movie)
        setYoutubeLink(movie)
        handleButtons()
        fetchLocalMovie(movie)
    }

    private fun fetchLocalMovie(movie: Movie) {
        GlobalScope.launch {
            val savedMovie: Movie? = moviesRepository.getAll().find { it.id == movie.id }
            withContext(Dispatchers.Main) {
                savedMovie?.let { this@MovieDetailsActivity.movie = it }
                savedMovie?.let { updateButtonsText(it) }
            }
        }
    }

    private fun setYoutubeLink(movie: Movie) {
        lifecycle.addObserver(video_player_details)
        val video: Video? = movie.videos.find { it.site == "YouTube" }
        video?.let {
            video_player_details.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(video.key, 0f)
                }
            })
        }
    }

    private fun handleButtons() {
        button_add_favorite.setOnClickListener {
            movie?.let { movie ->
                movie.isFavorite = !(movie.isFavorite ?: false)
                updateMovie(movie)
            }
        }
        button_add_watched.setOnClickListener {
            movie?.let { movie ->
                movie.isWatched = !(movie.isWatched ?: false)
                updateMovie(movie)
            }
        }
    }

    private fun updateButtonsText(movie: Movie) {
        button_add_favorite.text = if (movie.isFavorite == true) "REMOVE FAVORITE" else "ADD TO FAVORITE"
        button_add_watched.text = if (movie.isWatched == true) "REMOVE WATCHED" else "ADD TO WATCHED"
    }

    private fun updateMovie(movie: Movie) {
        GlobalScope.launch {
            moviesRepository.save(movie)
            withContext(Dispatchers.Main) {
                updateButtonsText(movie)
            }
        }
    }

    private fun populateMovieDetails(movie: Movie) {
        title_details.text = movie.title
        date_details.text = movie.release_date
        genre_details.text = movie.genres.map { it.name }.joinToString(", ")
        description_details.text = movie.overview
    }

    private fun populatePoster(movie: Movie) {
        val hasPoster: Boolean = movie.poster_path?.isNotBlank() ?: false
        if (hasPoster) {
            poster_details.isVisible = true
            val imageUrl = POSTER_URL + movie.poster_path
            ImageLoader.loadImage(imageUrl, poster_details, this)
        } else {
            poster_details.isVisible = false
        }
    }
}
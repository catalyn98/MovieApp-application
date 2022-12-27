package com.mobiversal.movieapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mobiversal.movieapplication.actor.ActorsRepository
import com.mobiversal.movieapplication.actor.FavouriteActor
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var genreRepository = GenreRepository.instance
    private var actorRepository = ActorsRepository.instance
    private var hasActor = false
    private var hasGenre = false
    private var selectedGenres: List<Genre> = emptyList()
    private var selectedActors: List<FavouriteActor> = emptyList()

    private fun getSelectedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedActors = actorRepository.getAll()
            hasActor = true
            setScreen()
        }
    }

    private fun getSelectedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedGenres = genreRepository.getAll()
            hasGenre = true
            setScreen()
        }
    }

    private fun setScreen(){
        if(hasActor&&hasGenre){
            GlobalScope.launch (Dispatchers.IO){
                if(selectedActors.isNotEmpty() || selectedGenres.isNotEmpty())
                startActivity(Intent(this@SplashActivity , ActivityMenuHamburger::class.java))
                else
                startActivity(Intent(this@SplashActivity , MainActivity::class.java))
            }
        }
    }

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            getSelectedActors()
            getSelectedGenres()
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
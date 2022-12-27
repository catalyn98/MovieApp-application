package com.mobiversal.movieapplication.genres_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.MainActivity
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.genre.Genre
import com.mobiversal.movieapplication.genre.GenreRepository
import kotlinx.android.synthetic.main.activity_genres.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Genres : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    val list: List<Genre> = ArrayList()
    private var genres: List<Genre> = emptyList()

    companion object {
        val TAG = Genres::class.java.simpleName
    }

    private fun setupRecycleView() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_genres.layoutManager = llm
        rv_genres.adapter = GenresAdapter(genres)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        getGenres()
        save_genre.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                genreRepository.deleteAll()
                genreRepository.saveAll(getSelectedGenres())
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun getGenres(): List<Genre>? {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genreRepository.getAllRemote()
            genres.let { genres ->
                withContext(Dispatchers.Main) {
                    Log.d(Genres.TAG, genres.firstOrNull()?.name?:"not found")
                    insertPreselectedItems ()
                    setupRecycleView()
                }
            }
        }
        return genres
    }

    fun getSelectedGenres(): List<Genre> {
        return genres.filter { genre -> genre.isSelected }
    }

    fun insertPreselectedItems () {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenre: List<Genre> = genreRepository.getAll()
            genres.forEach { genre -> genre.isSelected = savedGenre.contains(genre) }
        }
    }
}
package com.mobiversal.movieapplication.actors_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.movieapplication.MainActivity
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.actor.ActorsRepository
import com.mobiversal.movieapplication.actor.FavouriteActor
import kotlinx.android.synthetic.main.activity_actors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Actors : AppCompatActivity() {

    private val actorRepository = ActorsRepository.instance
    val list: List<FavouriteActor> = ArrayList()
    private var actors: List<FavouriteActor> = emptyList()

    companion object {
        val TAG = Actors::class.java.simpleName
    }

    private fun setupRecycleView() {
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_actors.layoutManager = llm
        rv_actors.adapter = ActorsAdapter(actors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)
        getActors()
        save_actor.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                actorRepository.deleteAll()
                actorRepository.saveAll(getSelectedActors())
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

        fun getActors(): List<FavouriteActor>? {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorRepository.getAllRemote()
            actors.let { actors ->
                withContext(Dispatchers.Main) {
                    Log.d(Actors.TAG, actors.firstOrNull()?.name?:"not found")
                    insertPreselectedItems ()
                    setupRecycleView()
                }
                }
            }
        return actors
    }

    fun getSelectedActors(): List<FavouriteActor> {
        return actors.filter { actor -> actor.isSelected }
    }

    fun insertPreselectedItems () {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActor: List<FavouriteActor> = actorRepository.getAll()
            actors.forEach { actor -> actor.isSelected = savedActor.contains(actor) }
        }
    }
    }
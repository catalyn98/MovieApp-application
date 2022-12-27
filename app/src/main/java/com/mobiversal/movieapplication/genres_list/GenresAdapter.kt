package com.mobiversal.movieapplication.genres_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiversal.movieapplication.R
import com.mobiversal.movieapplication.genre.Genre
import kotlinx.android.synthetic.main.actors_list_item.view.cb_selected
import kotlinx.android.synthetic.main.genres_list_item.view.*

class GenresAdapter(private val genresList: List<Genre>?) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        return GenresViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.genres_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return genresList?.size ?: 0
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre: Genre? = genresList?.get(position)
        genre?.let{
            holder.bind(genre)
        }
    }

    inner class GenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genre: Genre){
            itemView.tv_genre_name.text= genre.name
            itemView.cb_selected.setOnCheckedChangeListener(null)
            itemView.cb_selected.isChecked = genre.isSelected
            itemView.cb_selected.setOnCheckedChangeListener { buttonView, isChecked -> genre.isSelected = isChecked }
        }
    }
}
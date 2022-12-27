package com.mobiversal.movieapplication.ui.save

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mobiversal.movieapplication.R

class SavedMoviesPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when(position ){
            0 -> return FavoriteMoviesFragment()
            1 -> return WatchedMoviesFragment()
            else -> return FavoriteMoviesFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return context.getString(R.string.favorite_tab)
            1 -> return context.getString(R.string.watched_tab)
            else -> return context.getString(R.string.favorite_tab)
        }
    }
}
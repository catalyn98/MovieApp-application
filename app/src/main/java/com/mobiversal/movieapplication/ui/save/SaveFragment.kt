package com.mobiversal.movieapplication.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mobiversal.movieapplication.R

class SaveFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_save, container, false)
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val savedMoviesTabLayout : TabLayout = view.findViewById(R.id.tabLayout)
    val savedMoviesViewPager : ViewPager = view.findViewById(R.id.viewPager)
    val adapter: SavedMoviesPagerAdapter = SavedMoviesPagerAdapter(view.context, childFragmentManager)
    savedMoviesViewPager.adapter = adapter
    savedMoviesTabLayout.setupWithViewPager(savedMoviesViewPager)
  }
  }

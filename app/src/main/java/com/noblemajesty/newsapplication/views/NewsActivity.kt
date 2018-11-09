package com.noblemajesty.newsapplication.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.ActivityNewsBinding
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, NewsFragment())
                .commit()
        initializeBottomNavigationBar()
        viewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
    }

    private fun initializeBottomNavigationBar() {
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.news -> { goToNews();true }
                R.id.sports -> { goToSports(); true }
                R.id.food -> { goToFood(); true }
                R.id.movies -> { goToMovies(); true }
                else -> true
            }
        }
    }

    private fun goToNews() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, NewsFragment())
                .commit()
    }

    private fun goToSports() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SportsFragment())
                .commit()
    }

    private fun goToFood() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FoodFragment())
                .commit()
    }

    private fun goToMovies() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MoviesFragment())
                .commit()
    }

}

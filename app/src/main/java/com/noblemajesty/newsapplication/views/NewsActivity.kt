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
        viewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
        initializeBottomNavigationBar()
    }

    private fun initializeBottomNavigationBar() {
        binding.bottomNavigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.news -> { goToFragment(NewsFragment());true }
                R.id.sports -> { goToFragment(SportsFragment()); true }
                R.id.food -> { goToFragment(FoodFragment()); true }
                else -> true
            }
        }
    }

    private fun goToFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
    }

}

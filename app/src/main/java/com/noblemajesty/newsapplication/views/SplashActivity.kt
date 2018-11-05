package com.noblemajesty.newsapplication.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.models.getFood.NYTimesFoodResponse
import com.noblemajesty.newsapplication.models.getMovies.NYTimesMoviesResponse
import com.noblemajesty.newsapplication.models.getNews.NYTimesNewsResponse
import com.noblemajesty.newsapplication.models.getSports.NYTimesSportsResponse
import com.noblemajesty.newsapplication.utils.ErrorMessage
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.SplashActivityViewModel
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(SplashActivityViewModel::class.java)

        if (NetworkConnectivity(this).isConnected()) {
            val apiResponse = viewModel.getData()
            if (apiResponse.containsKey("ERROR")) {
                goToNewsActivityWithError((apiResponse["ERROR"] as Exception).message!!)
            } else {
                Log.e("API response", "$apiResponse")
            }
        } else {
            goToNewsActivityWithError(ErrorMessage.NO_NETWORK_DETECTED)
        }

    }

    private fun goToNewsActivityWithError(error: String) {
        val newsActivityIntent = Intent(this, NewsActivity::class.java)
        newsActivityIntent.putExtra("ERROR", error)
        startActivity(newsActivityIntent)
        finish()
    }

    private fun goToNewsActivity (newsResponse: NYTimesNewsResponse,
                                 sportsResponse: NYTimesSportsResponse,
                                 foodResponse: NYTimesFoodResponse,
                                 moviesResponse: NYTimesMoviesResponse) {
        val newsActivityIntent = Intent(this, NewsActivity::class.java)
        newsActivityIntent.putExtra("NEWS", newsResponse.toJson().toString())
        newsActivityIntent.putExtra("SPORTS", sportsResponse.toJson().toString())
        newsActivityIntent.putExtra("FOOD", foodResponse.toJson().toString())
        newsActivityIntent.putExtra("MOVIES", moviesResponse.toJson().toString())
        startActivity(newsActivityIntent)
        finish()
    }

}

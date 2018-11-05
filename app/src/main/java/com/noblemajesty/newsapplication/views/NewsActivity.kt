package com.noblemajesty.newsapplication.views

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.support.design.widget.Snackbar
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.ActivityNewsBinding
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        viewModel = ViewModelProviders.of(this).get(NewsActivityViewModel::class.java)
    }

     fun displaySnackbar(text: String, callback: () -> Unit) {
        Snackbar.make(binding.newsActivity, text.toUpperCase(), Snackbar.LENGTH_LONG)
                .setAction("RETRY") { callback() }
                .show()
    }

}

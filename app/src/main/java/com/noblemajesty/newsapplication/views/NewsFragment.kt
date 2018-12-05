package com.noblemajesty.newsapplication.views


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.adapters.Adapter
import com.noblemajesty.newsapplication.databinding.FragmentNewsBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import kotlinx.android.synthetic.main.activity_news.*


/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsActivityViewModel
    private lateinit var newsResponse: NYTimesResponse
    private val newsAdapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        initializeRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsSwipeRefresh.setOnRefreshListener(this)
        getData()
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.newsSwipeRefresh) { makeAPICall() }
    }

    private fun initializeRecyclerView() {
        binding.newsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun makeAPICall() {
        if (NetworkConnectivity(activity!!).isConnected()) {
            binding.display = true
            viewModel.fetchNews({
                newsResponse = it
                newsAdapter.update(newsResponse.results)
                binding.display = false
            }, {})
        } else {
            displaySnackbar(activity!!.newsActivity, "No internet connection", ::makeAPICall) }
    }

    private fun getData() {
        viewModel.news?.let {
            newsResponse = it
            newsAdapter.update(it.results)
        } ?: makeAPICall()
    }
}

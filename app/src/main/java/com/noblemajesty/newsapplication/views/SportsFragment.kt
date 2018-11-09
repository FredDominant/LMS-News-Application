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
import com.noblemajesty.newsapplication.databinding.FragmentSportsBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import kotlinx.android.synthetic.main.activity_news.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SportsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentSportsBinding
    private lateinit var viewModel: NewsActivityViewModel
    private lateinit var sportsResponse: NYTimesResponse
    private val sportsAdapter by lazy { Adapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sports, container, false)
        initializeRecyclerView()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sportsSwipeRefresh.setOnRefreshListener(this)
        getData()
    }

    private fun initializeRecyclerView() {
        binding.sportsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = sportsAdapter
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.sportsSwipeRefresh) { makeAPICall() }
    }

    private fun makeAPICall() {
        if (NetworkConnectivity(activity!!).isConnected()) {
            binding.display = true
            viewModel.fetchSports({
                sportsResponse = it
                Log.e("News Response", "$sportsResponse")
                sportsAdapter.update(sportsResponse.results)
                binding.display = false
            }, {
                displaySnackbar(activity!!.newsActivity, it.message!!, ::makeAPICall)
            })
        } else {
            displaySnackbar(activity!!.newsActivity, "No internet connection", ::makeAPICall) }
    }

    private fun getData() {
        viewModel.sports?.let {
            sportsResponse = it
            sportsAdapter.update(it.results)
        } ?: makeAPICall()
    }

}

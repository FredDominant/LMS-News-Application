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
import com.noblemajesty.newsapplication.adapters.NewsAdapter
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.databinding.FragmentFoodBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel.Companion.FOOD
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_news.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var viewModel: NewsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodSwipeRefresh.setOnRefreshListener(this)
        getData()
    }

    private fun initializeRecyclerView(list: RealmResults<FoodNews>) {
        binding.foodRecyclerView.apply {
            setHasFixedSize(true)
            adapter = NewsAdapter(list)
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getData() {
        val data = viewModel.loadDataFromDB(FoodNews::class.java)
        initializeRecyclerView(data!!)
//        viewModel.food?.let {
//            foodAdapter.update(it.results)
//        } ?: makeAPICall()
    }

    private fun makeAPICall() {
        if (NetworkConnectivity(activity!!).isConnected()) {
            binding.display = true
            viewModel.getDataFromAPI(NewsActivityViewModel.NEWS) {
                getData()
            }
//            binding.display = true
//             viewModel.getDataFromAPI(FOOD, {
//                 foodAdapter.update(it.results)
//             })
        } else {
            Log.e("No Newtork", "herrreeeeee")
            displaySnackbar(activity!!.newsActivity, "No internet connection", ::makeAPICall) }
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.foodSwipeRefresh) { makeAPICall() }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}

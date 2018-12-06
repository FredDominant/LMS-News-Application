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
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.databinding.FragmentNewsBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel.Companion.NEWS
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_news.*


/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsActivityViewModel
//    private val newsAdapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
//        initializeRecyclerView()
        binding.display = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        makeAPICall()
        binding.newsSwipeRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.newsSwipeRefresh) { makeAPICall() }
    }

    private fun initializeRecyclerView(list: RealmResults<HomeNews>) {
//        binding.newsRecyclerView.apply {
//            setHasFixedSize(true)
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
//        }
        binding.newsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = NewsAdapter(list)
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun makeAPICall() {
        if (NetworkConnectivity(activity!!).isConnected()) {
            binding.display = true
            Log.e("Making calllllll", "calllll")
            viewModel.getDataFromAPI(NEWS) {
                getData()
            }
//            viewModel.getDataFromAPI(NEWS, {
//                newsResponse = it
////                newsAdapter.update(it.results)
//            })
        } else {
            displaySnackbar(activity!!.newsActivity, "No internet connection", ::makeAPICall) }
    }

    private fun getData() {
        val data = viewModel.loadDataFromDB(HomeNews::class.java)
        Log.e("data here is >>>>", "$data")
        initializeRecyclerView(data!!)
        binding.display = false
//        viewModel.news?.let {
//            newsResponse = it
////            newsAdapter.update(it.results)
//        } ?: makeAPICall()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}

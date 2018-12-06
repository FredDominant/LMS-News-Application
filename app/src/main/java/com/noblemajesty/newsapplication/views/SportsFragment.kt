package com.noblemajesty.newsapplication.views


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.adapters.Adapter
import com.noblemajesty.newsapplication.adapters.NewsAdapter
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.databinding.FragmentSportsBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel.Companion.SPORTS
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_news.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SportsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentSportsBinding
    private lateinit var viewModel: NewsActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sports, container, false)
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

    private fun initializeRecyclerView(list: RealmResults<SportsNews>) {
        binding.sportsRecyclerView.apply {
            setHasFixedSize(true)
            adapter = NewsAdapter(list)
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.sportsSwipeRefresh) { makeAPICall() }
    }

    private fun makeAPICall() {
        if (NetworkConnectivity(activity!!).isConnected()) {
            binding.display = true
            viewModel.getDataFromAPI(NewsActivityViewModel.SPORTS) {
                getData()
            }
//            viewModel.getDataFromAPI(SPORTS, {
//                sportsResponse = it
//                sportsAdapter.update(it.results)
//            })
        } else {
            displaySnackbar(activity!!.newsActivity, "No internet connection", ::makeAPICall) }
    }

    private fun getData() {
        val data = viewModel.loadDataFromDB(SportsNews::class.java)
        initializeRecyclerView(data!!)
//        viewModel.sports?.let {
//            sportsResponse = it
//            sportsAdapter.update(it.results)
//        } ?: makeAPICall()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }

}

package com.noblemajesty.newsapplication.views


import android.arch.lifecycle.Observer
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
import com.noblemajesty.newsapplication.adapters.NewsAdapter
import com.noblemajesty.newsapplication.databinding.FragmentFoodBinding
import com.noblemajesty.newsapplication.utils.Constants
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import kotlinx.android.synthetic.main.activity_news.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var viewModel: NewsActivityViewModel
    private val foodAdapter by lazy { NewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false)
        initializeRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodSwipeRefresh.setOnRefreshListener(this)
        getData()
    }

    private fun initializeRecyclerView() {
        binding.foodRecyclerView.apply {
            setHasFixedSize(true)
            adapter = foodAdapter
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getData() {
        if (!NetworkConnectivity(activity!!).isConnected()) {
            displaySnackbar(activity!!.newsActivity, "check you internet", ::getData)
        }
        viewModel.fetchNewsFromDataBase(Constants.FOOD)
        viewModel.newsArray.observe(this, Observer {
            it?.let { newsList ->
                if (!newsList.isEmpty()) {
                    foodAdapter.updateList(it)
                    binding.display = false
                }
            }
        })
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.foodSwipeRefresh) { getData() }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}

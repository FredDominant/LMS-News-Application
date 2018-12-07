package com.noblemajesty.newsapplication.views


import android.annotation.SuppressLint
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
import com.noblemajesty.newsapplication.adapters.NewsAdapter
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.databinding.FragmentNewsBinding
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.utils.NetworkConnectivity
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel
import com.noblemajesty.newsapplication.viewmodels.NewsActivityViewModel.Companion.NEWS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news.*


/**
 * A simple [Fragment] subclass.
 *
 */
class NewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsActivityViewModel
    private val newsAdapter by lazy { NewsAdapter<HomeNews>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(NewsActivityViewModel::class.java)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        initializeRecyclerView()
        Log.e("Hereeeee", "lllllllllllllll")
        getData()
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsSwipeRefresh.setOnRefreshListener(this)
        binding.display = true
    }

    override fun onRefresh() {
        onSwipeRefresh(binding.newsSwipeRefresh) { getData() }
    }

    private fun initializeRecyclerView() {
        binding.newsRecyclerView.apply {
            this.adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        }
    }

    @SuppressLint("CheckResult")
    private fun getData() {
        if (!NetworkConnectivity(activity!!).isConnected()) {
            displaySnackbar(activity!!.newsActivity, "check you internet", ::getData)
        }
        viewModel.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.e("repository model", "$it")
                            if (it.isNotEmpty()) {
                                Log.e("news size", "${it.size}")
                                newsAdapter.updateList(it)
                                binding.display = false
                            }
                        },
                        { Log.e("repository model Error", "$it")
                            it.printStackTrace()}
                )

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
    }
}

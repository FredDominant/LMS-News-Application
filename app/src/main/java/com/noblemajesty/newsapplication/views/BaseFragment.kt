package com.noblemajesty.newsapplication.views

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View

open class BaseFragment: Fragment() {

    fun displaySnackbar(view: View,  errorMessage: String, retryFunction: () -> Unit) {
        Snackbar.make(view, errorMessage.toUpperCase(), Snackbar.LENGTH_LONG)
                .setAction("TRY AGAIN") { retryFunction() }
                .show()
    }

    fun onSwipeRefresh(swipeRefreshLayout: SwipeRefreshLayout, refreshFunction: () -> Unit) {
        swipeRefreshLayout.isRefreshing = true
        refreshFunction()
        swipeRefreshLayout.isRefreshing = false
    }
}
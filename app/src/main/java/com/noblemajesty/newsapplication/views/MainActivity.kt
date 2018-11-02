package com.noblemajesty.newsapplication.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitInstance = NYTimesRetrofitBuilder
                .getInstance()
                .createService(NYTimesService::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val request = retrofitInstance.getNews()
            val response = request.await()
            if (response.status == "OK") {
                Log.e("Result is", "${response.results}")
            } else {
                Log.e("Unsuccessful", "result")
            }
        }
    }
}

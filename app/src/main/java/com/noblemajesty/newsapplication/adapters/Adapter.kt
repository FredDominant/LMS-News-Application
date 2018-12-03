package com.noblemajesty.newsapplication.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import com.noblemajesty.newsapplication.models.Result

class Adapter: RecyclerView.Adapter<ViewHolder>() {

    private lateinit var binding: NewsRowItemBinding
    private val newsResult = mutableListOf<Result>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setValues(newsResult[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_row_item, parent, false )
        return ViewHolder(binding)
    }

    override fun getItemCount() = newsResult.size

    fun update(updates: List<Result>) {
        newsResult.clear()
        newsResult.addAll(updates)
        notifyDataSetChanged()
    }
}
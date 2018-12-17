package com.noblemajesty.newsapplication.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.database.News
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    lateinit var binding: NewsRowItemBinding
    private val listItems = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_row_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() :Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setValues(listItems[position])
    }

    fun updateList(updates: List<News>) {
        listItems.clear()
        listItems.addAll(updates)
        notifyDataSetChanged()
    }
}
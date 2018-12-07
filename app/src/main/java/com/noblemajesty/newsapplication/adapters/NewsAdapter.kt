package com.noblemajesty.newsapplication.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import io.realm.RealmObject

class NewsAdapter <T: RealmObject>(): RecyclerView.Adapter<NewsViewHolder<T>>() {

    lateinit var binding: NewsRowItemBinding
    private val listItems = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_row_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() :Int {
        Log.e("inside Adapter Count", "${listItems.size}")
        return listItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder<T>, position: Int) {
        Log.e("inside Bind Viewholder", "$position")
        holder.setValues(listItems[position])
    }

    fun updateList(updates: List<T>) {
        listItems.clear()
        listItems.addAll(updates)
        notifyDataSetChanged()
    }
}
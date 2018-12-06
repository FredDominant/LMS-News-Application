package com.noblemajesty.newsapplication.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import io.realm.RealmObject
import io.realm.RealmResults

class NewsAdapter <T: RealmObject>(private var listItems: RealmResults<T>): RecyclerView.Adapter<NewsViewHolder<T>>() {

    lateinit var binding: NewsRowItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_row_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() :Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder<T>, position: Int) {
        holder.setValues(listItems[position])
    }

//    fun updateList(updates: RealmResults<T>) {
//        listItems.clear()
//        listItems.addAll(updates)
//        notifyDataSetChanged()
//    }
}
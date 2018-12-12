package com.noblemajesty.newsapplication.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.database.News
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import com.squareup.picasso.Picasso

class NewsViewHolder(private val binding: NewsRowItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun setValues(data: News) {
        Log.e("data in viewHolder", "hereeeeeeeeeeeee")
                binding.image = data.image
                binding.newsAbstract = data.abstract
                binding.title = data.title
                binding.byline = data.byline
                binding.publishedDate = data.publishedDate
        binding.executePendingBindings()
    }

    private fun loadImage(drawable: Int?, uri: String?) {
        drawable?.let {
            Picasso.get()
                    .load(it)
                    .error(binding.root.resources.getDrawable(R.drawable.picc, null))
                    .into(binding.newsImage)
        }

        uri?.let {
            Picasso.get()
                    .load(it)
                    .error(binding.root.resources.getDrawable(R.drawable.picc, null))
                    .into(binding.newsImage)
        }
    }
}
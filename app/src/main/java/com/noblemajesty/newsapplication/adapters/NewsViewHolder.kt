package com.noblemajesty.newsapplication.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import com.squareup.picasso.Picasso

class NewsViewHolder<T>(private val binding: NewsRowItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun setValues(data: T) {
        Log.e("data in viewHolder", "hereeeeeeeeeeeee")
        when (data) {
            is HomeNews -> {
                binding.image = data.image
                binding.newsAbstract = data.abstract
                binding.title = data.title
                binding.byline = data.byline
                binding.publishedDate = data.publishedDate
            }
            is SportsNews -> {
                binding.image = data.image
                binding.newsAbstract = data.abstract
                binding.title = data.title
                binding.byline = data.byline
                binding.publishedDate = data.publishedDate
            }
            is FoodNews -> {
                binding.image = data.image
                binding.newsAbstract = data.abstract
                binding.title = data.title
                binding.byline = data.byline
                binding.publishedDate = data.publishedDate
            }
        }
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
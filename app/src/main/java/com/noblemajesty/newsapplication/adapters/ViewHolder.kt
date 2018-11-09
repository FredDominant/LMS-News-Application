package com.noblemajesty.newsapplication.adapters

import android.support.v7.widget.RecyclerView
import com.noblemajesty.newsapplication.R
import com.noblemajesty.newsapplication.databinding.NewsRowItemBinding
import com.noblemajesty.newsapplication.models.Result
import com.squareup.picasso.Picasso

class ViewHolder(private var binding: NewsRowItemBinding):
        RecyclerView.ViewHolder(binding.root) {

    fun setValues(newsArticle: Result) {
        binding.apply {
            data = newsArticle
            if (newsArticle.multimedia.isNotEmpty()) loadImage(null, newsArticle.multimedia[3].url)
            else loadImage(R.drawable.picc, null)
        }
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

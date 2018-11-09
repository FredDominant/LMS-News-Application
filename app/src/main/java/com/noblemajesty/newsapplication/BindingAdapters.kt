package com.noblemajesty.newsapplication

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                        .load(url)
                        .error(R.drawable.picc)
                        .into(view)
            } ?: Picasso.get()
                    .load(R.drawable.picc)
                    .error(R.drawable.picc)
                    .into(view)
        }
    }
}
package com.noblemajesty.newsapplication

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

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

@BindingAdapter("formatTime")
fun formatTime(view: TextView, timeStamp: String?) {
    timeStamp?.let {

    }
}

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
        val splittedTime = timeStamp.split("T")[0]
        val day = splittedTime.split("-")[2]
        val month = splittedTime.split("-")[1]
        val year = splittedTime.split("-")[0]
        var formattedMonth = ""
        when (month) {
            "1" -> { formattedMonth = "Jan." }
            "2" -> { formattedMonth = "Feb." }
            "3" -> { formattedMonth = "Mar." }
            "4" -> { formattedMonth = "Apr." }
            "5" -> { formattedMonth = "May." }
            "6" -> { formattedMonth = "Jun." }
            "7" -> { formattedMonth = "Jul." }
            "8" -> { formattedMonth = "Aug." }
            "9" -> { formattedMonth = "Sep." }
            "10" -> { formattedMonth = "Oct." }
            "11" -> { formattedMonth = "Nov." }
            "12" -> { formattedMonth = "Dec." }
        }
        view.text = "$formattedMonth $day, $year"
    }
}

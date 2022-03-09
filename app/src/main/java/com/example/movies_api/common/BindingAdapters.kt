package com.example.movies_api.common

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movies_api.R


@BindingAdapter("urlToImage")
fun urlToImage(view:ImageView,str:String?){

    str?.let {
        val options = RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)

        Glide.with(view).setDefaultRequestOptions(options).load(str).into(view)

    }


}

@BindingAdapter("setRatingView")
fun setRatingView(ratingBar: RatingBar,rating:String?){

    if(ratingBar!=null && rating!=null){
        val ratingInFloat = rating!!.toFloat()

        ratingBar.rating = ratingInFloat
    }


}
package com.natanbrito.countriesapp.utils

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.natanbrito.countriesapp.R

fun getProgressBarDrawable(context: Context): CircularProgressDrawable = CircularProgressDrawable(context)
        .apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

fun AppCompatImageView.loadImageUrl(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
}

@BindingAdapter("android:loadImage")
fun loadImage(view: AppCompatImageView, uri: String?){
    view.loadImageUrl(uri, getProgressBarDrawable(view.context))
}

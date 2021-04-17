package com.example.nasaproject.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nasaproject.R


/*  Kotlin Extension
fun String.myExt(myParameterName: String){

    println(myParameterName)
}*/

fun ImageView.downloadfromurl(url: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)

}
fun placeHolderProgressBar(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
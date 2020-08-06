package br.itbam.statisticcovid.views.bindingAdapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("changeImage")
fun ImageView.changeImage(url: Drawable){
    setImageDrawable(url)
}
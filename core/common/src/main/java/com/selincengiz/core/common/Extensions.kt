package com.selincengiz.core.common


import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {
    fun ImageView.loadUrl(url: String?) {
        Glide.with(this.context).load(url).placeholder(R.drawable.default_product)
            .error(R.drawable.default_product).into(this)
    }

    fun ImageView.loadUrlCircle(url: String?) {
        Glide.with(this.context).load(url).circleCrop().placeholder(R.drawable.default_product)
            .error(R.drawable.default_product).into(this)
    }
}
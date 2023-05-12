package com.example.secondapplication.presentationlayer.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.secondapplication.R
import com.example.secondapplication.objects.Item
import com.bumptech.glide.request.target.Target


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected val image = view.findViewById<ImageView>(R.id.image)

    fun bind(item: Item) {
        val outer = this
        Glide.with(itemView).load(item.imageValue()).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                var progressBar_cyclic = itemView.findViewById<ProgressBar>(R.id.progressBar_cyclic)
                progressBar_cyclic.visibility = INVISIBLE
                var button = itemView.findViewById<Button>(R.id.reload_button)
                button.visibility = VISIBLE
                button.setOnClickListener {
                    button.visibility = INVISIBLE
                    progressBar_cyclic.visibility = VISIBLE
                    outer.bind(item)
                }

                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {

                itemView.findViewById<ProgressBar>(R.id.progressBar_cyclic).visibility =
                    INVISIBLE
                itemView.findViewById<Button>(R.id.reload_button).visibility = INVISIBLE

                return false
            }

        }).into(image)
    }
}
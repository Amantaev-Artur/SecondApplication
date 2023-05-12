package com.example.secondapplication.presentationlayer.adapters

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.example.secondapplication.R
import com.example.secondapplication.objects.Item

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected val value = view.findViewById<TextView>(R.id.value)
    protected val image = view.findViewById<ImageView>(R.id.image)

    protected val imageLoader by lazy { Picasso.get() }

    fun bind(item: Item) {
        val outer = this
        imageLoader.load(item.imageValue()).into(image, object : Callback {
            override fun onSuccess() {
                itemView.findViewById<ProgressBar>(R.id.progressBar_cyclic).visibility =
                    INVISIBLE
                itemView.findViewById<Button>(R.id.reload_button).visibility = INVISIBLE
            }

            override fun onError(e: Exception?) {
                var progressBar_cyclic = itemView.findViewById<ProgressBar>(R.id.progressBar_cyclic)
                progressBar_cyclic.visibility = INVISIBLE
                var button = itemView.findViewById<Button>(R.id.reload_button)
                button.visibility = VISIBLE
                button.setOnClickListener {
                    button.visibility = INVISIBLE
                    progressBar_cyclic.visibility = VISIBLE
                    outer.bind(item)
                }
            }
        })
    }
}
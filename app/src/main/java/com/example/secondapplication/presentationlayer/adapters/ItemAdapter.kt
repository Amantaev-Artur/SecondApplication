package com.example.secondapplication.presentationlayer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.ListAdapter
import com.example.secondapplication.R
import com.example.secondapplication.objects.Item

class ItemAdapter : ListAdapter<Item, ItemViewHolder>(ItemDifferCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.findViewById<ProgressBar>(R.id.progressBar_cyclic).visibility = View.VISIBLE
        val item = getItem(position)
        holder.bind(item)
    }
}
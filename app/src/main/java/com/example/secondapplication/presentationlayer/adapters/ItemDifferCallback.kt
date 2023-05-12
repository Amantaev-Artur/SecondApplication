package com.example.secondapplication.presentationlayer.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.secondapplication.objects.Item

class ItemDifferCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return false
    }
}
package com.onopry.gif_app.app.app.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.onopry.gif_app.app.data.model.GifItem

class GIFDiffCallback: DiffUtil.ItemCallback<GifItem>() {
    override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
        return (oldItem.title == newItem.title)
                && (oldItem.rating == newItem.rating)
                && (oldItem.user == newItem.user)
                && (oldItem.username == newItem.username)
                && (oldItem.importDatetime == newItem.importDatetime)
                && (oldItem.trendingDatetime == newItem.trendingDatetime)
    }
}
package com.onopry.gif_app.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.gif_app.R
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.databinding.ItemGifBinding

class GIFAdapter : PagingDataAdapter<GifItem, GIFAdapter.GIFViewHolder>(GIFDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GIFViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGifBinding.inflate(layoutInflater, parent, false)
        return GIFViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GIFViewHolder, position: Int) {
        holder.onBind(
            getItem(position) ?: return
        )
    }

    class GIFViewHolder(
        private val binding: ItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(gif: GifItem) {
            binding.gifTitleTv.text = gif.title
            loadGif(gif)
        }

        private fun loadGif(gif: GifItem) {
            val imageWebpUrl = gif.images.fixedHeight.webp
            if (imageWebpUrl.isNotBlank())
                Glide.with(binding.root)
                    .asGif()
                    .load(imageWebpUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.gifImg)
        }
    }
}
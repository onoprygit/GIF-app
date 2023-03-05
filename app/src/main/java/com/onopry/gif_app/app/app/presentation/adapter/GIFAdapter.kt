package com.onopry.gif_app.app.app.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onopry.gif_app.R
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.databinding.ItemGifBinding

typealias OnGifClickListener = (id: String) -> Unit

class GIFAdapter(
    private val clickListener: OnGifClickListener
) : PagingDataAdapter<GifItem, GIFAdapter.GIFViewHolder>(GIFDiffCallback()) {
    val TAG = this::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GIFViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGifBinding.inflate(layoutInflater, parent, false)
        return GIFViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GIFViewHolder, position: Int) {
        Log.d(GIFAdapter::class.java.simpleName, "onBindViewHolder: ")
        holder.onBind(
            getItem(position)!!, clickListener
        )
    }

    class GIFViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            Log.d(GIFViewHolder::class.java.simpleName, "GIFViewHolder-init:")
        }

        fun onBind(gif: GifItem, listener: OnGifClickListener) {
            Log.d(GIFViewHolder::class.java.simpleName, "GIFViewHolder-onBind:")
            binding.gifTitleTv.text = gif.title
            loadGif(gif)

            binding.root.setOnClickListener { listener.invoke(gif.id) }
        }

        private fun loadGif(gif: GifItem) {
            gif.images.fixedHeight.url.let { url ->
                url?.let {
                    if (url.isNotBlank())
                        Glide.with(binding.root)
                            .asGif()
                            .load(url)
                            .placeholder(R.drawable.placeholder)
                            .into(binding.gifImg)
                }
            }
        }
    }
}
package com.example.unsplash.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.unsplash.R
import com.example.unsplash.databinding.RvItemsBinding
import com.example.unsplash.models.UnsplashPhoto

class GalleryAdapter : PagingDataAdapter<UnsplashPhoto, GalleryAdapter.ViewHolder>(DIFFER_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class ViewHolder(private val binding: RvItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: UnsplashPhoto) {
            itemView.setOnClickListener { onItemClickListener?.invoke(photo) }
            binding.apply {
                textViewUserName.text = photo.user.username
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        }

    }

    companion object {
        private val DIFFER_CALLBACK = object : DiffUtil.ItemCallback<UnsplashPhoto>() {

            override fun areItemsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    // onClick listener lambda
    var onItemClickListener: ((UnsplashPhoto) -> Unit)? = null

}
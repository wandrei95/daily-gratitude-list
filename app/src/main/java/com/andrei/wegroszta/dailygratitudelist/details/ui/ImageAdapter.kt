package com.andrei.wegroszta.dailygratitudelist.details.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrei.wegroszta.dailygratitudelist.databinding.ItemImageBinding
import com.bumptech.glide.Glide

internal class ImageAdapter : ListAdapter<GratitudeImageUiState, ImageAdapter.ImageViewHolder>(
    ImageDiffCallback
) {

    private object ImageDiffCallback : DiffUtil.ItemCallback<GratitudeImageUiState>() {
        override fun areItemsTheSame(
            oldItem: GratitudeImageUiState,
            newItem: GratitudeImageUiState
        ): Boolean {
            return oldItem.photoUrl == newItem.photoUrl
        }

        override fun areContentsTheSame(
            oldItem: GratitudeImageUiState,
            newItem: GratitudeImageUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    internal class ImageViewHolder(
        val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUiState: GratitudeImageUiState) {
            Glide.with(itemView.context)
                .load(imageUiState.photoUrl)
                .into(binding.ivPhoto)

            binding.container.isEnabled = imageUiState.isSelected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUiState = getItem(position)

//        holder.itemView.setOnClickListener {
//            imageUiState.onImageSelected()
//        }

        holder.binding.ivPhoto.setOnClickListener {
            imageUiState.onImageSelected()
        }

        holder.bind(imageUiState)
    }

}

package com.andrei.wegroszta.dailygratitudelist.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrei.wegroszta.dailygratitudelist.databinding.ItemTagBinding

internal class TagsAdapter : ListAdapter<String, TagsAdapter.TagsViewHolder>(
    TagsDiffCallback
) {

    private object TagsDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

    internal class TagsViewHolder(
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: String) {
            binding.tvTag.text = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val binding = ItemTagBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val tag = getItem(position)

        holder.bind(tag)
    }
}
package com.andrei.wegroszta.dailygratitudelist.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrei.wegroszta.dailygratitudelist.R
import com.andrei.wegroszta.dailygratitudelist.common.SpacingItemDecoration
import com.andrei.wegroszta.dailygratitudelist.common.TagsAdapter
import com.andrei.wegroszta.dailygratitudelist.databinding.ItemGratitudeBinding
import com.bumptech.glide.Glide

internal class GratitudesRVAdapter :
    ListAdapter<GratitudeListItemUiState, GratitudesRVAdapter.GratitudeViewHolder>(
        GratitudesDiffCallback
    ) {

    var onGratitudeClicked: (gratitudeId: String) -> Unit = {}

    private object GratitudesDiffCallback : DiffUtil.ItemCallback<GratitudeListItemUiState>() {
        override fun areItemsTheSame(
            oldItem: GratitudeListItemUiState,
            newItem: GratitudeListItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GratitudeListItemUiState,
            newItem: GratitudeListItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    internal class GratitudeViewHolder(
        private val binding: ItemGratitudeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GratitudeListItemUiState) = with(binding) {
            tvDate.text = item.date
            tvSummary.text = item.summary

            rvTags.isVisible = item.tags.isNotEmpty()
            setupRVTags(item)

            item.image?.let {
                Glide.with(root.context)
                    .load(it)
                    .into(ivPhoto)
            }
            ivPhoto.isVisible = item.image != null
        }

        private fun setupRVTags(
            item: GratitudeListItemUiState
        ) {
            if (item.tags.isNotEmpty()) {
                val adapter = TagsAdapter()
                val layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

                val spacing =
                    (binding.root.context.resources.getDimension(R.dimen.spacing_small) / 2).toInt()
                binding.rvTags.addItemDecoration(SpacingItemDecoration(rightSpacing = spacing))

                binding.rvTags.layoutManager = layoutManager
                binding.rvTags.adapter = adapter

                adapter.submitList(item.tags)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GratitudeViewHolder {
        val binding = ItemGratitudeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return GratitudeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GratitudeViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        holder.itemView.setOnClickListener {
            onGratitudeClicked(item.id)
        }
    }
}

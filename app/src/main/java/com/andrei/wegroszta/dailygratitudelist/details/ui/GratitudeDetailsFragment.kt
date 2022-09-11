package com.andrei.wegroszta.dailygratitudelist.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrei.wegroszta.dailygratitudelist.R
import com.andrei.wegroszta.dailygratitudelist.common.SpacingItemDecoration
import com.andrei.wegroszta.dailygratitudelist.common.TagsAdapter
import com.andrei.wegroszta.dailygratitudelist.databinding.FragmentGratitudeDetailsBinding
import com.andrei.wegroszta.dailygratitudelist.ext.doOnLifecycleStartedState
import com.andrei.wegroszta.dailygratitudelist.ext.setSupportTitle
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GratitudeDetailsFragment : Fragment() {
    private var _binding: FragmentGratitudeDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GratitudeDetailsViewModel by viewModels()

    private val args: GratitudeDetailsFragmentArgs by navArgs()
    private val gratitudeId: String
        get() = args.gratitudeId

    private val tagsAdapter: TagsAdapter by lazy { TagsAdapter() }
    private val imagesAdapter: ImageAdapter by lazy { ImageAdapter() }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setSupportTitle("Details")

        _binding = FragmentGratitudeDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRVTags()
        setupRVImages()

        doOnLifecycleStartedState {
            viewModel.gratitudeDetailsUiState.collect { onNewUIState(it) }
        }

        viewModel.loadGratitude(gratitudeId)
    }

    private fun onNewUIState(uiState: GratitudeDetailsUiState) = with(uiState) {
        if (isLoading) {
            binding.loadingContainer.isVisible = true
            return
        }
        binding.loadingContainer.isVisible = false

        binding.tvDate.text = uiState.date
//        binding.tvSummary.text = uiState.summary
        binding.tvSummary.text = "testttt"

        val selectedPhoto = photos.find { it.isSelected }
        binding.ivSelectedPhoto.isVisible = selectedPhoto != null
        selectedPhoto?.let {
            Glide.with(requireContext())
                .load(it.photoUrl)
                .into(binding.ivSelectedPhoto)
        }

        binding.rvImages.isVisible = photos.isNotEmpty()
        if (photos.isNotEmpty()) {
            imagesAdapter.submitList(photos)
        }

        binding.rvTags.isVisible = tags.isNotEmpty()
        if (tags.isNotEmpty()) {
            tagsAdapter.submitList(tags)
        }
    }

    private fun setupRVTags() = with(binding.rvTags) {
        layoutManager = FlexboxLayoutManager(rootView.context)

        val spacing = (resources.getDimension(R.dimen.spacing_medium) / 2).toInt()
        addItemDecoration(SpacingItemDecoration(topSpacing = spacing, rightSpacing = spacing))

        adapter = tagsAdapter
    }

    private fun setupRVImages() = with(binding.rvImages) {
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val spacing = (resources.getDimension(R.dimen.spacing_medium) / 2).toInt()
        addItemDecoration(SpacingItemDecoration(topSpacing = spacing, rightSpacing = spacing))

        adapter = imagesAdapter
    }
}

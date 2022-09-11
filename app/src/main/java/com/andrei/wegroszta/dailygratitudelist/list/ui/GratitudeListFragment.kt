package com.andrei.wegroszta.dailygratitudelist.list.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrei.wegroszta.dailygratitudelist.R
import com.andrei.wegroszta.dailygratitudelist.common.SpacingItemDecoration
import com.andrei.wegroszta.dailygratitudelist.databinding.FragmentGratitudeListBinding
import com.andrei.wegroszta.dailygratitudelist.ext.doOnLifecycleStartedState
import com.andrei.wegroszta.dailygratitudelist.ext.setSupportTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GratitudeListFragment : Fragment() {

    private var _binding: FragmentGratitudeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GratitudeListViewModel by viewModels()

    private val gratitudesRVAdapter: GratitudesRVAdapter by lazy {
        GratitudesRVAdapter().apply {
            onGratitudeClicked = this@GratitudeListFragment::onGratitudeSelected
        }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setSupportTitle("Gratitude List")

        _binding = FragmentGratitudeListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRVGratitudes()

        doOnLifecycleStartedState {
            viewModel.gratitudeListUiState.collect { onNewUIState(it) }
        }
    }

    private fun onNewUIState(uiState: GratitudeListUiState) = with(uiState) {
        if (isLoading) {
            binding.loadingContainer.isVisible = true
            return
        }
        binding.loadingContainer.isVisible = false

        if (items.isNotEmpty()) {
            gratitudesRVAdapter.submitList(items)
        }
    }

    private fun setupRVGratitudes() = with(binding.rvGratitudes) {
        LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }.also {
            layoutManager = it
        }

        val spacing = (resources.getDimension(R.dimen.spacing_medium) / 2).toInt()
        addItemDecoration(SpacingItemDecoration(bottomSpacing = spacing))

        adapter = gratitudesRVAdapter
    }

    private fun onGratitudeSelected(gratitudeId: String) {
        val action =
            GratitudeListFragmentDirections.actionGratitudeListFragmentToGratitudeDetailsFragment(
                gratitudeId
            )


        findNavController().navigate(action)
    }
}

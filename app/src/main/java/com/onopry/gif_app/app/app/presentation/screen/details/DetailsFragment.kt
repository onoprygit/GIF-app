package com.onopry.gif_app.app.app.presentation.screen.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.onopry.gif_app.R
import com.onopry.gif_app.app.common.gone
import com.onopry.gif_app.app.common.hide
import com.onopry.gif_app.app.common.observeRepeated
import com.onopry.gif_app.app.common.show
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        viewModel.getDetails(args.gifId)

        observeRepeated {
            viewModel.screenState.collect { state ->
                Log.d("DetailsFragment", "onViewCreated: ${state::class.java.simpleName}")
                when(state) {
                    is
                    DetailsUiState.Content -> setDetails(state.data)
                    is DetailsUiState.Loading -> setViewsLoadState()
                    is DetailsUiState.Error -> setViewsErrorState(state.msg)
                    is DetailsUiState.Empty -> setViewsLoadState()
                }
            }
        }

        binding.switeToRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.switeToRefresh.isRefreshing = false
        }
    }

    private fun setViewsContentState() {
        with(binding.loadingPart) {
            binding.contentLayout.show()
            progressBar.gone()
            errorImage.gone()
            errorMessageTv.gone()
            tryAgainButton.gone()
        }
    }

    private fun setViewsErrorState(msg: String) {
        with(binding.loadingPart) {
            binding.contentLayout.show()
            progressBar.gone()
            errorImage.show()
            errorMessageTv.show()
            tryAgainButton.show()

            errorMessageTv.text = msg
            tryAgainButton.setOnClickListener {
                viewModel.refresh()
            }
        }
    }

    private fun setViewsLoadState() {
        with(binding.loadingPart) {
            binding.contentLayout.hide()
            progressBar.show()
            errorImage.gone()
            errorMessageTv.gone()
            tryAgainButton.gone()
        }
    }

    private fun setDetails(gif: GifItem) {
        setViewsContentState()
        binding.username.text = gif.username

        Glide.with(requireContext())
            .asGif()
            .load(gif.images.fixedHeight.url)
            .placeholder(R.drawable.placeholder)
            .into(binding.gifImage)


        binding.imageTitle.text = gif.title

        Glide.with(requireContext())
            .load(gif.user?.avatarUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.userAvatar)

        binding.userDescription.text = gif.user?.description
    }
}
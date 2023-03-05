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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
                val TAG = "DetailsLoadState_TAG"
                when (state) {
                    is DetailsUiState.Content -> {
                        Log.d(TAG, "Content")
                        setDetails(state.data)
                    }
                    is DetailsUiState.Loading -> {
                        Log.d(TAG, "Loading")
                        setViewsLoadState()
                    }
                    is DetailsUiState.Error -> {
                        Log.d(TAG, "Error")
                        setViewsErrorState(state.msg)
                    }
                    is DetailsUiState.Empty -> {
                        Log.d(TAG, "Empty")
                        setViewsLoadState()
                    }
                }
            }
        }

        binding.switeToRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.switeToRefresh.isRefreshing = false
        }
    }

/*    private fun setViewsContentState() {
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
    }*/

    private fun setViewsContentState() {
//        binding.shimmerInclude.shimmerGifImage.
//        binding.shimmerInclude.avatarShimmer
        binding.shimmerInclude.shimmerUsername.gone()
        binding.shimmerInclude.shimmerImageTitle.gone()
        binding.contentLayout.show()
    }

    private fun setViewsErrorState(msg: String) {
        binding.shimmerInclude.frameLayout.gone()
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
        binding.contentLayout.hide()
        binding.shimmerInclude.shimmerGifImage.startShimmer()
        binding.shimmerInclude.avatarShimmer.startShimmer()
        binding.shimmerInclude.shimmerUsername.startShimmer()
        binding.shimmerInclude.shimmerImageTitle.startShimmer()
        binding.shimmerInclude.shimmerGifImage.show()
        binding.shimmerInclude.avatarShimmer.show()
        binding.shimmerInclude.shimmerUsername.show()
        binding.shimmerInclude.shimmerImageTitle.show()
        /*with(binding.loadingPart) {
            binding.contentLayout.hide()
            progressBar.show()
            errorImage.gone()
            errorMessageTv.gone()
            tryAgainButton.gone()
        }*/
    }

    private fun setDetails(gif: GifItem) {
        setViewsContentState()
        if (gif.username.isNotBlank()) {
            binding.username.text = gif.username
        } else {
            binding.username.text = "user not found"
        }

        Glide.with(requireContext())
            .asGif()
            .load(gif.images.fixedHeight.url)
            .addListener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.shimmerInclude.shimmerGifImage.stopShimmer()
                    binding.shimmerInclude.shimmerGifImage.gone()
                    return false
                }

            })
            .placeholder(R.drawable.placeholder)
            .into(binding.gifImage)


        binding.imageTitle.text = gif.title

        Glide.with(requireContext())
            .asGif()
            .load(gif.user?.avatarUrl)
            .addListener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.shimmerInclude.avatarShimmer.stopShimmer()
                    binding.shimmerInclude.avatarShimmer.gone()
                    return false
                }

            })
            .placeholder(R.drawable.placeholder)
            .into(binding.userAvatar)

        binding.userDescription.text = gif.user?.description ?: "No description"
    }
}
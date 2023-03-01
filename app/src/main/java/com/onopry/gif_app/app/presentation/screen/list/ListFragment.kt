package com.onopry.gif_app.app.presentation.screen.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.onopry.gif_app.R
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.presentation.adapter.GIFAdapter
import com.onopry.gif_app.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        val adapter = GIFAdapter()

        binding.companiesRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.gifFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }
}
package com.onopry.gif_app.app.app.presentation.screen.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onopry.gif_app.R
import com.onopry.gif_app.app.app.presentation.adapter.GIFAdapter
import com.onopry.gif_app.app.common.observeRepeated
import com.onopry.gif_app.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var pagingAdapter: GIFAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        pagingAdapter = GIFAdapter()
        binding.companiesRecycler.adapter = pagingAdapter
        binding.companiesRecycler.layoutManager =
            StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)

        submitNoSearchedData()
        setupSearch()
    }

    private fun setupSearch() {
        binding.searchEditText.doOnTextChanged { query, _, _, _ ->
            query?.let {
                viewModel.sendSearchQuery(query)
            }
        }

        binding.inputLayout.setEndIconOnClickListener {
            binding.searchEditText.clearFocus()
            binding.searchEditText.setText("")
            submitNoSearchedData()
        }


        observeRepeated {
            viewModel.searchFlow.collectLatest {
                if (it != null) {
                    pagingAdapter.submitData(it)
                }
            }
        }
    }

    private fun submitNoSearchedData() {
        observeRepeated {
            viewModel.gifFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}
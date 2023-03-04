package com.onopry.gif_app.app.presentation.screen.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var pagingAdapter: GIFAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        pagingAdapter = GIFAdapter()
        binding.companiesRecycler.adapter = pagingAdapter
        binding.companiesRecycler.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewModel.gifFlow.collectLatest { pagingData ->
                Log.d(ListFragment::class.java.simpleName, "CollectLatest pagingData=${pagingData}")
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}
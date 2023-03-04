package com.onopry.gif_app.app.presentation.screen.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.onopry.gif_app.app.common.NetworkConst
import com.onopry.gif_app.app.data.datasource.GIFPagingSource
import com.onopry.gif_app.app.data.datasource.GifService
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: Repository,
    private val apiService: GifService
) : ViewModel() {
    val TAG = this::class.java.simpleName

    init {
        Log.d(TAG, "init: ")

        /*viewModelScope.launch {
            val response = apiService.getTradingGIFs(0)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Log.d(TAG, response.body()?.gifItemList?.size.toString())
                } else {
                    Log.d(TAG, "response body NULL")
                }
            } else {
                Log.d(TAG, "response FALSE")
            }
        }*/

    }

    val gifFlow: Flow<PagingData<GifItem>> = repo.getPagedGIFs().cachedIn(viewModelScope)


    fun getPagedGIFs(): Flow<PagingData<GifItem>> {
        val factory = GIFPagingSource(apiService)
        val pager = Pager(
            config = PagingConfig(
                pageSize = NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                factory
            }
        )
        val pagerFlow = pager.flow
        return pagerFlow
    }
}
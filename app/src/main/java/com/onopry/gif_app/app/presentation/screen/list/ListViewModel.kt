package com.onopry.gif_app.app.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class ListViewModel(private val repo: Repository) : ViewModel() {

    val gifFlow = flow<PagingData<GifItem>> {
        repo.getPagedGIFs()
    }.cachedIn(viewModelScope)


}
package com.onopry.gif_app.app.data.repository

import androidx.paging.PagingData
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.SingleGiphyResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
//    suspend fun getGIFs(): ApiResult<List<GifItem>>
    fun getPagedGIFs(): Flow<PagingData<GifItem>>
    fun searchQuery(query: String): Flow<PagingData<GifItem>>
    suspend fun getById(id: String): ApiResult<SingleGiphyResponse>
}
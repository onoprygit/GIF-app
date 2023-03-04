package com.onopry.gif_app.app.data.repository

import androidx.paging.PagingData
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GifItem
import kotlinx.coroutines.flow.Flow

interface Repository {
//    suspend fun getGIFs(): ApiResult<List<GifItem>>
    fun getPagedGIFs(): Flow<PagingData<GifItem>>
}
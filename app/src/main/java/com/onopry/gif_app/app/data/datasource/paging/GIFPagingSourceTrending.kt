package com.onopry.gif_app.app.data.datasource.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.datasource.network.GifService
import com.onopry.gif_app.app.data.model.GifItem


class GIFPagingSourceTrending(
//    private val remoteDataSource: RemoteDataSource,
    private val apiService: GifService,
    private val itemsLimit: Int = DEFAULT_REQUEST_ITEMS_LIMIT
) : BasePagingSource<Int, GifItem>(itemsLimit) {
    val TAG = this::class.java.simpleName
    private val startingKey = 0

    init {
        Log.d(PagingSource::class.java.simpleName, "init")
    }

    override fun getRefreshKey(state: PagingState<Int, GifItem>): Int? {
        return null
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        Log.d(PagingSource::class.java.simpleName, "load() starts")
        val itemOffset = params.key ?: startingKey
        return apiRequest(params) {
            apiService.getTradingGIFs(itemOffset, 25)
        }
    }
}

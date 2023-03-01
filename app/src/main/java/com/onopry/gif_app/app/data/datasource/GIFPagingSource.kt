package com.onopry.gif_app.app.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onopry.gif_app.app.common.*
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.Pagination
import javax.inject.Inject

class GIFPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val itemsLimit: Int = DEFAULT_REQUEST_ITEMS_LIMIT
) : PagingSource<Int, GifItem>() {
    private val startingKey = 25

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        Log.d("GIFPagingSource", "load()")
        val itemOffset = params.key ?: startingKey

        return try {
            when (
                val response = remoteDataSource.getGIFs(itemOffset, params.loadSize)
            ) {
                is ApiSuccess -> {
                    val gifs: List<GifItem> = response.data.gifItemList
                    val pagination: Pagination = response.data.pagination
                    LoadResult.Page(
                        data = gifs,
                        prevKey = if (itemOffset == startingKey) null else itemOffset - itemsLimit,
                        nextKey = if (pagination.totalCount - pagination.offset >= itemsLimit) pagination.offset + itemsLimit else null
                    )
                }
                is ApiError ->
                    LoadResult.Error(
                        throwable = GifLoadException(
                            code = response.code,
                            message = response.message
                        )
                    )
                is ApiException ->
                    LoadResult.Error(response.e)
            }

        } catch (e: Exception) {
            return LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifItem>): Int? {
//        val anchorPos = state.anchorPosition ?: return null
//        val itemOffset = state.closestItemToPosition(anchorPos) ?: return null
//        return state.anchorPosition?.let {
//            state.closestItemToPosition(it)?.id
//        }
        return null
    }
}
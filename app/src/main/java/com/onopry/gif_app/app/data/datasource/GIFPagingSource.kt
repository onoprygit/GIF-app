package com.onopry.gif_app.app.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onopry.gif_app.app.common.*
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.Pagination

class GIFPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val itemsLimit: Int = DEFAULT_REQUEST_ITEMS_LIMIT
) : PagingSource<Int, GifItem>() {
    private val startingKey = 25

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        val itemOffset = params.key ?: startingKey

        return try {
            val gifs: List<GifItem>
            val pagination: Pagination

            when (val response = remoteDataSource.getGIFs(itemOffset, itemsLimit)) {
                is ApiSuccess -> {
                    gifs = response.data.gifItemList
                    pagination = response.data.pagination
                }
                is ApiError ->
                    return LoadResult.Error(
                        throwable = GifLoadException(
                            code = response.code,
                            message = response.message
                        )
                    )
                is ApiException ->
                    return LoadResult.Error(response.e)
            }


            return LoadResult.Page(
                data = gifs,
                prevKey = if (itemOffset == startingKey) null else itemOffset - itemsLimit,
                nextKey = if (pagination.totalCount - pagination.offset >= itemsLimit) pagination.offset + itemsLimit else null
            )
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
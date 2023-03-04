package com.onopry.gif_app.app.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.GiphyResponse
import retrofit2.HttpException
import retrofit2.Response

abstract class BasePagingSource<K: Any, V: Any>(private val itemsLimit: Int) :
    PagingSource<K, V>() {
    private val startingKey = 0
    override fun getRefreshKey(state: PagingState<K, V>): K? {
        return null
    }
    protected suspend fun apiRequest(
        params: LoadParams<Int>,
        request: suspend () -> Response<GiphyResponse>
    ): LoadResult<Int, GifItem> {
        val itemOffset = params.key ?: startingKey
        return try {
            val response = request.invoke()
//            val response = apiService.getTradingGIFs(itemOffset, 25)
            val pagination = response.body()!!.pagination
            val data = response.body()?.gifItemList ?: emptyList()
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = data,
                    prevKey = if (itemOffset == startingKey) null else itemOffset - params.loadSize,
                    nextKey = if (pagination.totalCount - pagination.offset >= itemsLimit) pagination.offset + itemsLimit else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(throwable = e)
        }
    }

    abstract override suspend fun load(params: LoadParams<K>): LoadResult<K, V>

}
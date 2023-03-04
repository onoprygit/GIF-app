package com.onopry.gif_app.app.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onopry.gif_app.app.common.*
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.Pagination
import com.onopry.gif_app.app.data.repository.GIFRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

class GIFPagingSource(
//    private val remoteDataSource: RemoteDataSource,
    private val apiService: GifService,
    private val itemsLimit: Int = DEFAULT_REQUEST_ITEMS_LIMIT
) : PagingSource<Int, GifItem>() {
    val TAG = this::class.java.simpleName
    private val startingKey = 0
    init {
        Log.d(PagingSource::class.java.simpleName, "init")
    }

    override fun getRefreshKey(state: PagingState<Int, GifItem>): Int? {
        Log.d(TAG, "getRefreshKey: ")
        Log.d(PagingSource::class.java.simpleName, "RefreshKey()")
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
        Log.d(PagingSource::class.java.simpleName, "load() starts")
        val itemOffset = params.key ?: startingKey

        return try {
            val response = apiService.getTradingGIFs(itemOffset, 25)
            val pagination = response.body()!!.pagination
            val data = response.body()?.gifItemList ?: emptyList()
            if (response.isSuccessful) {
                Log.d(TAG, "load: success")
                LoadResult.Page(
                    data = data,
                    prevKey = if (itemOffset == startingKey) null else itemOffset - params.loadSize,
                    nextKey = if (data.size < params.loadSize) null else itemOffset + params.loadSize
//                    nextKey = if (pagination.totalCount - pagination.offset >= itemsLimit) pagination.offset + itemsLimit else null
                )
            } else {
                LoadResult.Error(HttpException(response))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(throwable = e)
        }
    }
}

/*override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifItem> {
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
    }*/


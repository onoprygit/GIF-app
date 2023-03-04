package com.onopry.gif_app.app.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onopry.gif_app.app.common.ApiError
import com.onopry.gif_app.app.common.ApiException
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.common.ApiSuccess
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.datasource.GIFPagingSource
import com.onopry.gif_app.app.data.datasource.GifService
import com.onopry.gif_app.app.data.datasource.RemoteDataSource
import com.onopry.gif_app.app.data.datasource.wrapRetrofitRequest
import com.onopry.gif_app.app.data.model.GifItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GIFRepository @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val apiService: GifService
) : Repository {
    override fun getPagedGIFs(): Flow<PagingData<GifItem>> {
        Log.d(GIFRepository::class.java.simpleName, "getPagedGIFs")

        val pager = Pager(
            config = PagingConfig(
                pageSize = DEFAULT_REQUEST_ITEMS_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GIFPagingSource(apiService)
            }
        ).flow
        return pager
    }
}
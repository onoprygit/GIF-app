package com.onopry.gif_app.app.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import com.onopry.gif_app.app.data.datasource.paging.GIFPagingSourceTrending
import com.onopry.gif_app.app.data.datasource.network.GifService
import com.onopry.gif_app.app.data.datasource.RemoteDataSource
import com.onopry.gif_app.app.data.datasource.paging.GIFPagingSourceSearch
import com.onopry.gif_app.app.data.model.GifItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GIFRepository @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val apiService: GifService
) : Repository {
    override fun getPagedGIFs(): Flow<PagingData<GifItem>> = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_REQUEST_ITEMS_LIMIT,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GIFPagingSourceTrending(apiService = apiService)
        }
    ).flow

    override fun searchQuery(query: String): Flow<PagingData<GifItem>> =
        // todo: Возможно пейдж сайз нужно передавать в параметрах метода, а не константой
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_REQUEST_ITEMS_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GIFPagingSourceSearch(apiService = apiService, searchQuery = query)
            }
        ).flow
}
package com.onopry.gif_app.app.data.repository

import com.onopry.gif_app.app.common.ApiError
import com.onopry.gif_app.app.common.ApiException
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.common.ApiSuccess
import com.onopry.gif_app.app.data.datasource.GifService
import com.onopry.gif_app.app.data.datasource.RemoteDataSource
import com.onopry.gif_app.app.data.datasource.wrapRetrofitRequest
import com.onopry.gif_app.app.data.model.GifItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GIFRepository(
    private val dataSource: RemoteDataSource
) : Repository {
    override suspend fun getGIFs() = dataSource.getGIFs()
}
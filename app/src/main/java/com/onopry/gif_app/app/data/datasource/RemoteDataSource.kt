package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GifItem

interface RemoteDataSource {
    suspend fun getGIFs(): ApiResult<List<GifItem>>
}
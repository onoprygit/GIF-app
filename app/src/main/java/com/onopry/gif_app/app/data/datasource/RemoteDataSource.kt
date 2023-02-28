package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.model.GiphyResponse

interface RemoteDataSource {
    suspend fun getGIFs(
        itemIndex: Int = 0,
        limit: Int = 0
    ): ApiResult<GiphyResponse>
}
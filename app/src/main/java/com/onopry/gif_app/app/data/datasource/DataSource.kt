package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GiphyResponse
import com.onopry.gif_app.app.data.model.SingleGiphyResponse

interface DataSource {
    suspend fun getTrandingGIFs(
        itemIndex: Int,
        limit: Int
    ): ApiResult<GiphyResponse>

    suspend fun getGIFById(id: String): ApiResult<SingleGiphyResponse>
}
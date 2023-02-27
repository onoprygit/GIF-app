package com.onopry.gif_app.app.data.repository

import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.data.model.GifItem

interface Repository {
    suspend fun getGIFs(): ApiResult<List<GifItem>>
}
package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GiphyResponse(
    @Json(name = "data")
    val gifItemList: List<GifItem>,
    val pagination: Pagination
)
package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pagination(
    val count: Int,
    val offset: Int,
    @Json(name = "total_count")
    val totalCount: Int
)
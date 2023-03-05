package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifItem(
    val id: String,
    val url: String,
    val username: String,
    val title: String,
    val rating: String,
    val images: Images,
    val user: User?,
    @Json(name = "embed_url")
    val embedUrl: String,
    @Json(name = "import_datetime")
    val importDatetime: String,
    @Json(name = "trending_datetime")
    val trendingDatetime: String,
    )


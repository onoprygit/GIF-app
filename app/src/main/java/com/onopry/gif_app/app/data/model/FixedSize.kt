package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixedSize(
    val height: String,
    val mp4: String,
    @Json(name = "mp4_size")
    val mp4Size: String,
    val size: String,
    val url: String,
    val webp: String,
    @Json(name = "webp_size")
    val webpSize: String,
    val width: String
)
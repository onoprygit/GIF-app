package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    val original: FixedSize,
    @Json(name = "fixed_height")
    val fixedHeight: FixedSize,
    @Json(name = "fixed_width")
    val fixedWidth: FixedSize,
)
package com.onopry.gif_app.app.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnalyticsItem(
    val url: String
)
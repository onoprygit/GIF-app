package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Analytics(
    @Json(name = "onclick")
    val onClick: AnalyticsItem,
    @Json(name = "onload")
    val onLoad: AnalyticsItem,
    @Json(name = "onsent")
    val onSent: AnalyticsItem
)
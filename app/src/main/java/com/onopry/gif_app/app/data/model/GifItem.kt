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
    val user: User,
    @Json(name = "import_datetime")
    val importDatetime: String,
    @Json(name = "trending_datetime")
    val trendingDatetime: String,
    )
/*    val analytics: Analytics,
    val analytics_response_payload: String,
    val bitly_gif_url: String,
    val bitly_url: String,
    val content_url: String,
    val embed_url: String,

    val is_sticker: Int,
    val slug: String,
    val source: String,
    val source_post_url: String,
    val source_tld: String,

    val type: String,


    */

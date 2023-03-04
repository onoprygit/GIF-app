package com.onopry.gif_app.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "banner_image")
    val bannerImage: String,
    @Json(name = "banner_url")
    val bannerUrl: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "instagram_url")
    val instagramUrl: String,
    @Json(name = "is_verified")
    val isVerified: Boolean,
    @Json(name = "profile_url")
    val profileUrl: String,
    @Json(name = "website_url")
    val websiteUrl: String,

    val description: String,
    val username: String,
)
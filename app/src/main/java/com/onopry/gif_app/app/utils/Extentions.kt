package com.onopry.gif_app.app.utils

import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addQueryParam(
    name: String,
    key: String,
): OkHttpClient.Builder = this.addInterceptor { chain ->
    val request = chain.request()
    val url = request
        .url
        .newBuilder()
        .addQueryParameter(name, key)
        .build()
    val newRequest = request.newBuilder().url(url).build()
    chain.proceed(newRequest)
}
package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.data.model.GiphyResponse
import com.onopry.gif_app.app.common.NetworkConst
import com.onopry.gif_app.app.common.NetworkConst.API_KEY
import com.onopry.gif_app.app.common.NetworkConst.DEFAULT_REQUEST_ITEMS_LIMIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GifService {
    /*@GET("./movie/popular?api_key=e4833b4846dccc926e6dad24a6291ea8")
    suspend fun fetchMoviesPreviewList(): Response<MovieListResponseBody>*/

    @GET("./trending?api_key=dUvaqPT3WeT8eJu7MUN9CT0pNOwfwo5z")
    suspend fun getTradingGIFs(
        @Query(value = "offset") offset: Int = 0,
        @Query(value = "limit") limit: Int = DEFAULT_REQUEST_ITEMS_LIMIT,
//        @Path(value = "api_key") apiKey: String = API_KEY
    ): Response<GiphyResponse>
}
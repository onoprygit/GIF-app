package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.common.ApiError
import com.onopry.gif_app.app.common.ApiException
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.common.ApiSuccess
import com.onopry.gif_app.app.data.datasource.network.GifService
import com.onopry.gif_app.app.data.model.GiphyResponse
import retrofit2.HttpException
import javax.inject.Inject

/*

    suspend fun <T : Any> wrapRetrofitRequest(
    apiRequest: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val res = apiRequest()
        val body = res.body()

        if (res.isSuccessful && body != null)
            ApiSuccess(data = body)
        else
            ApiError(code = res.code(), message = res.message())
    } catch (e: HttpException) {
        ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiException(e)
    }
}

*/

class GIFApiDataSource @Inject constructor(private val apiService: GifService) : RemoteDataSource {
    override suspend fun getTrandingGIFs(
        itemIndex: Int,
        limit: Int
    ): ApiResult<GiphyResponse> {
        return try {
            val res = apiService.getTradingGIFs(offset = itemIndex, limit = limit)
            val body = res.body()

            if (res.isSuccessful && body != null)
                ApiSuccess(data = body)
            else
                ApiError(code = res.code(), message = res.message() ?: "Unexpected error occurs...")
        } catch (e: HttpException) {
            ApiError(code = e.code(), message = e.message() ?: "Unexpected error occurs...")
        } catch (e: Throwable) {
            ApiException(e)
        }
    }

    suspend fun searchQuery(
        itemIndex: Int,
        limit: Int, query: String
    ) {

    }

}
package com.onopry.gif_app.app.data.datasource

import com.onopry.gif_app.app.common.ApiError
import com.onopry.gif_app.app.common.ApiException
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.common.ApiSuccess
import retrofit2.HttpException
import retrofit2.Response

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
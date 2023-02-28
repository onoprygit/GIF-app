package com.onopry.gif_app.app.common

import okio.IOException

class GifLoadException(
    private val code: Int,
    message: String
): IOException(message = message)
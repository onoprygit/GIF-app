package com.onopry.gif_app.app.common

import java.io.IOException


class GifLoadException(
    private val code: Int,
    message: String
): IOException(message)
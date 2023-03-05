package com.onopry.gif_app.app.app.presentation.screen.details

import com.onopry.gif_app.app.data.model.GifItem

sealed class DetailsUiState {
    object Empty : DetailsUiState()
    class Error(val msg: String) : DetailsUiState()
    class Content(val data: GifItem) : DetailsUiState()
    object Loading : DetailsUiState()
}
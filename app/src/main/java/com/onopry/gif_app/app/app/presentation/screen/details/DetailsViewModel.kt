package com.onopry.gif_app.app.app.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onopry.gif_app.app.common.ApiError
import com.onopry.gif_app.app.common.ApiException
import com.onopry.gif_app.app.common.ApiResult
import com.onopry.gif_app.app.common.ApiSuccess
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val screenStateMutableFlow = MutableStateFlow<DetailsUiState>(DetailsUiState.Empty)
    val screenState = screenStateMutableFlow.asStateFlow()

    var currentId: String? = null
    fun getDetails(id: String) {
        currentId = id
        viewModelScope.launch {
            screenStateMutableFlow.emit(DetailsUiState.Loading)
            when (val response = repository.getById(id)) {
                is ApiSuccess -> screenStateMutableFlow.emit(DetailsUiState.Content(response.data.gifItem))
                is ApiError -> screenStateMutableFlow.emit(DetailsUiState.Error("${response.code} error: ${response.message}"))
                is ApiException -> screenStateMutableFlow.emit(
                    DetailsUiState.Error(
                        response.e.message ?: "Unexpected error occurred..."
                    )
                )
            }
        }
    }

    fun refresh() {
        currentId?.let {
            getDetails(it)
        }
    }
}
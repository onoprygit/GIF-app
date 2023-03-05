package com.onopry.gif_app.app.app.presentation.screen.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.onopry.gif_app.app.data.network.GifService
import com.onopry.gif_app.app.data.model.GifItem
import com.onopry.gif_app.app.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {
    private var searchQueryJob: Job? = null

    private val searchRequest = MutableStateFlow("")

    val gifFlow: Flow<PagingData<GifItem>> = repo.getPagedGIFs().cachedIn(viewModelScope)

    val searchFlow = MutableStateFlow<PagingData<GifItem>?>(null)

    private fun search(query: String) {
        searchQueryJob?.cancel()
        searchQueryJob = viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "search input: $query")
            repo.searchQuery(query)
                .cachedIn(viewModelScope)
                .collectLatest { searchFlow.emit(it) }
            searchQueryJob = null
        }
    }

    fun refresh(){}

    fun sendSearchQuery(userInput: CharSequence) {
        viewModelScope.launch {
            searchRequest.emit(userInput.toString())
        }
    }


    init {
        // todo Добавить проверку на 2 и более пробела, чтобы не искать впустую
        searchRequest.onEach { query ->
            if (query.isEmpty())
                searchQueryJob?.cancel()
        }.debounce(USERS_SEARCH_INPUT_DELAY)
            .onEach { query ->
                if (query.isNotEmpty())
                    search(query)
            }
            .launchIn(viewModelScope)
    }

    companion object {
        private const val USERS_SEARCH_INPUT_DELAY = 300L
        private const val TAG = "ListViewModel"
    }
}
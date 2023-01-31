package com.example.unsplash.ui.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.unsplash.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: Repository,
    state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query" // just a key to be used by state
        private const val DEFAULT_QUERY = ""
    }

    // to handle process death by saving last search query
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    // photos: LiveData<PagingData<UnsplashPhoto>> depends on the search query
    // search query will be either empty (DEFAULT_QUERY) -> getAllResults
    // or search query will have a value -> getSearchResults(query)
    // after fetching data and cache it in the room database
    val photos = currentQuery.switchMap { query ->
        if (query.isNotEmpty())
            repository.getSearchResults(query).cachedIn(viewModelScope)
        else repository.getAllResults().cachedIn(viewModelScope)
    }

    // search function changes the currentQuery.value to the recent search query
    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

}
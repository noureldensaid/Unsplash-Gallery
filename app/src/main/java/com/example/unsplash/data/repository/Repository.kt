package com.example.unsplash.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.unsplash.data.local.UnsplashDatabase
import com.example.unsplash.data.paging.UnsplashPagingSource
import com.example.unsplash.data.paging.UnsplashRemoteMediator
import com.example.unsplash.data.remote.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {
    fun getSearchResults(query: String) =
    // Pager class responsible for producing the PagingData stream
        // .It depends on the PagingSource to do this
        Pager(
            // PagingConfig is a class defines the parameters that determine paging behavior.
            //  This includes page size, whether placeholders are enabled, and so on.
            config = PagingConfig(
                pageSize = 15,
                maxSize = 100,
                enablePlaceholders = false,
            ),
            // The pagingSourceFactory lambda
            // should always return a brand new PagingSource when invoked
            // as PagingSource instances are not reusable.
            // provides an instance of the UnsplashPagingSource we just created.
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
            //PagingData is a type that wraps the data we've loaded
            // and helps the Paging library decide when to fetch more data,
            // and also make sure we don't request the same page twice
            // PagingData here is wrapped with liveData -->  LiveData<PagingData<UnsplashPhoto>>
        ).liveData

    fun getAllResults() =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                maxSize = 100,
                enablePlaceholders = false,
            ),
            remoteMediator = UnsplashRemoteMediator(
                unsplashDatabase = unsplashDatabase,
                unsplashApi = unsplashApi
            ),
            pagingSourceFactory = { unsplashDatabase.unsplashPhotoDao().getAll() }
        ).liveData
}
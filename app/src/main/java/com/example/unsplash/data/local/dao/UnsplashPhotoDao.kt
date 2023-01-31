package com.example.unsplash.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unsplash.models.UnsplashPhoto

@Dao
interface UnsplashPhotoDao {

    // as remote mediator needs to fetch data from database first "SOURCE OF TRUTH"
    @Query("SELECT * FROM unsplash_photo_table")
     fun getAll() : PagingSource<Int,UnsplashPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(unsplashPhotos: List<UnsplashPhoto>)

    @Query("DELETE FROM unsplash_photo_table")
    suspend fun deleteAll()


}
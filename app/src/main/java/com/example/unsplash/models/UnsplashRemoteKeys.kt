package com.example.unsplash.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsplash.utils.Constants.UNSPLASH_REMOTE_KEYS_TABLE

@Entity(tableName = UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey
    val id: String,
    val nextPage: Int?,
    val prevPage: Int?
)

package com.example.unsplash.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsplash.utils.Constants.UNSPLASH_PHOTO_TABLE
import kotlinx.parcelize.Parcelize
@Entity(tableName = UNSPLASH_PHOTO_TABLE)
@Parcelize
data class UnsplashPhoto(
    @PrimaryKey
    val id: String,
    val description: String?,
    @Embedded
    val urls: Urls,
    @Embedded
    val user: User
) : Parcelable {

    @Parcelize
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class User(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }
}
package com.example.unsplash.models
data class UnsplashResponse(
    val results: List<UnsplashPhoto>,
    val total: Int,
    val total_pages: Int
)
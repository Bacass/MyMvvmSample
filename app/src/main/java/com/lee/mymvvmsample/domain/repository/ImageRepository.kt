package com.lee.mymvvmsample.domain.repository

import com.lee.mymvvmsample.domain.model.ImageSearchResult

interface ImageRepository {
    suspend fun searchImages(
        query: String,
        page: Int,
        perPage: Int,
    ): Result<ImageSearchResult>
}

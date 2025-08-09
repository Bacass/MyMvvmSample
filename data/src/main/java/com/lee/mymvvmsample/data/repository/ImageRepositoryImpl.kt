package com.lee.mymvvmsample.data.repository

import com.lee.mymvvmsample.data.mapper.ImageMapper
import com.lee.mymvvmsample.data.network.ImageApiService
import com.lee.mymvvmsample.domain.model.ImageSearchResult
import com.lee.mymvvmsample.domain.model.Either
import com.lee.mymvvmsample.domain.model.Failure
import com.lee.mymvvmsample.domain.repository.ImageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(
    private val apiService: ImageApiService,
    private val imageMapper: ImageMapper,
) : ImageRepository {
    override suspend fun searchImages(
        query: String,
        page: Int,
        perPage: Int,
    ): Either<Failure, ImageSearchResult> {
        return try {
            val response = apiService.searchImage(
                key = com.lee.mymvvmsample.data.BuildConfig.PIXABAY_KEY,
                q = query,
                imageType = "photo",
                page = page,
                perPage = perPage,
            )

            if (response.isSuccessful) {
                val imageSearchResult = imageMapper.mapToDomain(response.body())
                if (imageSearchResult.images.isEmpty()) {
                    Either.Left(Failure.NoData)
                } else {
                    Either.Right(imageSearchResult)
                }
            } else {
                Either.Left(Failure.Server(response.code(), response.message()))
            }
        } catch (e: Exception) {
            Either.Left(Failure.Unknown(e.message))
        }
    }
}



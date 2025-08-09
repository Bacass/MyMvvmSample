package com.lee.mymvvmsample.data.repository

import com.lee.mymvvmsample.data.mapper.ImageMapper
import com.lee.mymvvmsample.data.network.ImageApiService
import com.lee.mymvvmsample.domain.model.ImageSearchResult
import com.lee.mymvvmsample.domain.model.Either
import com.lee.mymvvmsample.domain.model.Failure
import com.lee.mymvvmsample.domain.repository.ImageRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
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
        val response = apiService.searchImage(
            key = com.lee.mymvvmsample.data.BuildConfig.PIXABAY_KEY,
            q = query,
            imageType = "photo",
            page = page,
            perPage = perPage,
        )

        return when (response) {
            is ApiResponse.Success -> {
                val imageSearchResult = imageMapper.mapToDomain(response.data)
                if (imageSearchResult.images.isEmpty()) {
                    Either.Left(Failure.NoData)
                } else {
                    Either.Right(imageSearchResult)
                }
            }
            is ApiResponse.Failure.Error -> {
                Either.Left(Failure.Server(response.statusCode.code, response.message()))
            }
            is ApiResponse.Failure.Exception -> {
                Either.Left(Failure.Unknown(response.message))
            }
        }
    }
}



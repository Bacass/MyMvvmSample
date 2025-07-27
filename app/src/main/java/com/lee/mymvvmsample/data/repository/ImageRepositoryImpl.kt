package com.lee.mymvvmsample.data.repository

import com.lee.mymvvmsample.common.utils.Constants
import com.lee.mymvvmsample.data.mapper.ImageMapper
import com.lee.mymvvmsample.data.network.ImageApiService
import com.lee.mymvvmsample.domain.model.ImageSearchResult
import com.lee.mymvvmsample.domain.repository.ImageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl
    @Inject
    constructor(
        private val apiService: ImageApiService,
        private val imageMapper: ImageMapper,
    ) : ImageRepository {
        override suspend fun searchImages(
            query: String,
            page: Int,
            perPage: Int,
        ): Result<ImageSearchResult> {
            return try {
                val response =
                    apiService.searchImage(
                        key = Constants.PIXABAY_KEY,
                        q = query,
                        imageType = "photo",
                        page = page,
                        perPage = perPage,
                    )

                if (response.isSuccessful) {
                    val imageSearchResult = imageMapper.mapToDomain(response.body())
                    Result.success(imageSearchResult)
                } else {
                    Result.failure(Exception("네트워크 오류가 발생했습니다"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

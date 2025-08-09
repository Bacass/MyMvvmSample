package com.lee.mymvvmsample.domain.usecase

import com.lee.mymvvmsample.domain.model.ImageSearchResult
import com.lee.mymvvmsample.domain.repository.ImageRepository
import javax.inject.Inject
import com.lee.mymvvmsample.domain.model.Either
import com.lee.mymvvmsample.domain.model.Failure

class SearchImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int = 20,
    ): Either<Failure, ImageSearchResult> {
        if (query.isBlank()) {
            return Either.Left(Failure.InvalidInput("검색어를 입력해주세요"))
        }
        return imageRepository.searchImages(query, page, perPage)
    }
}



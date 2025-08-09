package com.lee.mymvvmsample.data.mapper

import com.lee.mymvvmsample.data.model.ImageHits
import com.lee.mymvvmsample.data.model.ImageObj
import com.lee.mymvvmsample.domain.model.Image
import com.lee.mymvvmsample.domain.model.ImageSearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageMapper @Inject constructor() {
    fun mapToDomain(imageObj: ImageObj?): ImageSearchResult {
        return ImageSearchResult(
            total = imageObj?.total ?: 0,
            totalHits = imageObj?.totalHits ?: 0,
            images = imageObj?.hits?.map { mapToDomain(it) } ?: emptyList(),
        )
    }

    private fun mapToDomain(imageHits: ImageHits): Image {
        return Image(
            id = imageHits.id,
            pageURL = imageHits.pageURL,
            type = imageHits.type,
            tags = imageHits.tags,
            previewURL = imageHits.previewURL,
            previewWidth = imageHits.previewWidth,
            previewHeight = imageHits.previewHeight,
            webformatURL = imageHits.webformatURL,
            webformatWidth = imageHits.webformatWidth,
            webformatHeight = imageHits.webformatHeight,
            largeImageURL = imageHits.largeImageURL,
            imageWidth = imageHits.imageWidth,
            imageHeight = imageHits.imageHeight,
            imageSize = imageHits.imageSize,
            views = imageHits.views,
            downloads = imageHits.downloads,
            favorites = imageHits.favorites,
            likes = imageHits.likes,
            comments = imageHits.comments,
            userId = imageHits.user_id,
            user = imageHits.user,
            userImageURL = imageHits.userImageURL,
        )
    }
}



package com.lee.mymvvmsample.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageObj {
    var total: Int = 0
    var totalHits: Int = 0
    var hits: MutableList<ImageHits> = mutableListOf()
}

data class ImageHits(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("pageURL")
    val pageURL: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("tags")
    val tags: String,
    @Expose
    @SerializedName("previewURL")
    val previewURL: String,
    @Expose
    @SerializedName("previewWidth")
    val previewWidth: Int,
    @Expose
    @SerializedName("previewHeight")
    val previewHeight: Int,
    @Expose
    @SerializedName("webformatURL")
    val webformatURL: String,
    @Expose
    @SerializedName("webformatWidth")
    val webformatWidth: Int,
    @Expose
    @SerializedName("webformatHeight")
    val webformatHeight: Int,
    @Expose
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @Expose
    @SerializedName("imageWidth")
    val imageWidth: Int,
    @Expose
    @SerializedName("imageHeight")
    val imageHeight: Int,
    @Expose
    @SerializedName("imageSize")
    val imageSize: Int,
    @Expose
    @SerializedName("views")
    val views: Int,
    @Expose
    @SerializedName("downloads")
    val downloads: Int,
    @Expose
    @SerializedName("favorites")
    val favorites: Int,
    @Expose
    @SerializedName("likes")
    val likes: Int,
    @Expose
    @SerializedName("comments")
    val comments: Int,
    @Expose
    @SerializedName("user_id")
    val user_id: Int,
    @Expose
    @SerializedName("user")
    val user: String,
    @Expose
    @SerializedName("userImageURL")
    val userImageURL: String
)